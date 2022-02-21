package com.mia.collabapp.firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

@Repository
@Log
public class FirebaseClient {

  private Firestore db;

  public FirebaseClient() {
    try {
      this.db = initialize();
    } catch (IOException e) {
      e.printStackTrace();
      log.warning("Error on initialize fb: " + e.getMessage());
    }
  }

  Firestore initialize() throws IOException {
    FileInputStream serviceAccount =
        new FileInputStream("src/main/resources/static/mia-collab-fb-firebase-adminsdk-n9o78-558f2e2422.json");

    // Use the application default credentials

    FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setProjectId("mia-collab-fb")
        .build();
    FirebaseApp.initializeApp(options);

    return FirestoreClient.getFirestore();
  }

  public Timestamp saveDoc(String collection, String docId, Map<String, Object> data) throws ExecutionException {

    DocumentReference docRef = db.collection(collection).document(docId);
    ApiFuture<WriteResult> result = docRef.set(data);

    try {
      WriteResult wr = result.get();
      return wr.getUpdateTime();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      log.warning(e.getMessage());
      throw new ExecutionException(e.getMessage(), e.getCause());
    } finally {
      log.warning("Error on set doc");
    }

    return null;
  }

  public <T> List<T> getCollection(String collection, Class<T> clazz) {
    List<T> result = null;
    // asynchronously retrieve
    ApiFuture<QuerySnapshot> query = db.collection(collection).get();
    // query.get() blocks on response
    QuerySnapshot querySnapshot = null;
    try {
      querySnapshot = query.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } finally {
      log.warning("Error on get collection");
    }

    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

    for (QueryDocumentSnapshot doc : documents) {
      result.add(doc.toObject(clazz));
    }

    return result;
  }

  public <T> List<T> getDocsByQuery(String collection, QueryFilters filter, String field, String value, Class<T> clazz)
      throws ExecutionException, InterruptedException {
    Query query = null;
    // Create a reference to the collection
    CollectionReference collectionRef = db.collection(collection);
    // Create a query against the collection.
    if (QueryFilters.EQUAL_TO.equals(filter)) {
      query = collectionRef.whereEqualTo(field, value);
    }

    if (query != null) {
      List<T> result = new ArrayList();
      // retrieve  query results asynchronously using query.get()
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      QuerySnapshot qs = null;
      try {
        qs = querySnapshot.get();
      } catch (InterruptedException e) {
        log.warning("Error on get querySnapshot");
        throw e;
      } catch (ExecutionException e) {
        log.warning("Error on get querySnapshot");
        throw e;
      }

      for (DocumentSnapshot document : qs.getDocuments()) {
        result.add(document.toObject(clazz));
      }

      return result;

    }

    return null;
  }
}
