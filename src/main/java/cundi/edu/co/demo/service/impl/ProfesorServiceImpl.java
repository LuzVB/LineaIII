package cundi.edu.co.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import cundi.edu.co.demo.config.FirebaseConfig;
import cundi.edu.co.demo.dto.ProfesorDto;
import cundi.edu.co.demo.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private FirebaseConfig fireBase;

	@Override
	public List<ProfesorDto> retornar() throws InterruptedException, ExecutionException {
		List<ProfesorDto> profesores = new ArrayList<>();
		ProfesorDto profesor;
		
		ApiFuture<QuerySnapshot> consultaApiFuture = getColeccion().get();
		for (DocumentSnapshot doc : consultaApiFuture.get().getDocuments()) {
			profesor = doc.toObject(ProfesorDto.class);
			profesor.setId(doc.getId());
			profesores.add(profesor);
        }
		
		return profesores;
	}

	@Override
	public void guardar(ProfesorDto profesor) throws InterruptedException, ExecutionException, Exception {
		Map<String, Object> datosProfesor = getDatosProfesor(profesor);

		ApiFuture<WriteResult> resultadoApiFuture = getColeccion().document().create(datosProfesor);

		if (null == resultadoApiFuture.get()) { //comprobar si se guardo correctamente 
			throw new Exception();
		}

	}

		
	@Override
	public void editar(ProfesorDto profesor) throws InterruptedException, ExecutionException, Exception {
		Map<String, Object> datosProfesor = getDatosProfesor(profesor);
		ApiFuture<WriteResult> resultadoApiFuture = getColeccion().document(profesor.getId()).set(datosProfesor);

		if (null == resultadoApiFuture.get()) { //comprobar si se guardo correctamente 
			throw new Exception();
		}
	}

	@Override
	public void eliminar(String idProfesor) throws InterruptedException, ExecutionException, Exception {
		ApiFuture<WriteResult> resultadoApiFuture = getColeccion().document(idProfesor).delete();


		if (null == resultadoApiFuture.get()) { //comprobar si se guardo correctamente 
			throw new Exception();
		}
	}
	
	private CollectionReference getColeccion() { //Saca la instancia de la collecion
		return fireBase.getFirestore().collection("profesores");
	}
	
	private Map<String, Object> getDatosProfesor(ProfesorDto profesor) { //Crea el map para la coleccion 
		Map<String, Object> datosProfesor = new HashMap<>();
		datosProfesor.put("apellido", profesor.getApellido());
		datosProfesor.put("cedula", profesor.getCedula());
		datosProfesor.put("correo", profesor.getCorreo());
		datosProfesor.put("nombre", profesor.getNombre());
		return datosProfesor;
	}

}
