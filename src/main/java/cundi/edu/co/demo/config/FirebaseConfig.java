package cundi.edu.co.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseConfig {

	@PostConstruct
    private  void  iniFirestore() throws IOException {

        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("lineaiii-firebase-account-info.json"); //Lee el archivo de configuracion

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://lineaiii.firebaseio.com/")
                .build();

        if(FirebaseApp.getApps().isEmpty()){ //En caso de que no se inicialice firebase
            FirebaseApp.initializeApp(options);
        }
    }

    public Firestore getFirestore(){ //obtener instancia 
        return FirestoreClient.getFirestore();
    }
}
