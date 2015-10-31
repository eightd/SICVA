package com.eightdevelopers.sicva.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.eightdevelopers.sicva.db.ConexionBD;

public class DescargaEvideciaDAO {
	
	
	public StreamedContent Escolaridad(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			System.out.println("dentro try ");
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM escolaridades WHERE usuarios_id_usuarios=? and id_escolaridad =?;");
			st.setInt(1, id);
			st.setInt(2, num);

			rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
			}
			conexionBD.cerrar();
			return file;
		} catch (Exception e) {

		}

		return file;
	}

	public StreamedContent ExperienciaLaboral(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			System.out.println("dentro try ");
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM experiencias_laborales WHERE usuarios_id_usuarios=? and id_experiencia_laboral=?;");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		    }
			conexionBD.cerrar();
			return file;

		    } catch (Exception e) {

		}

		return file;
	}

	public StreamedContent Ponencias(int id,int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			System.out.println("dentro try ");
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM cursos_ponencias  where usuarios_id_usuarios=? and id_curso_ponencia=?");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
			}
			conexionBD.cerrar();
			return file;
		} catch (Exception e) {

		}
		return file;
	}

	public StreamedContent Cursos(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			System.out.println("dentro try ");
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM asistencias_cursos where usuarios_id_usuarios=? and id_asistencia_curso=?;");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		    }
			conexionBD.cerrar();
			return file;

		} catch (Exception e) {

		}

		return file;
	}

	public StreamedContent Comisiones(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM comisiones WHERE usuarios_id_usuarios =? and id_comision=?");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		   }
			conexionBD.cerrar();
			return file;

		} catch (Exception e) {

		}

		return file;
	}

	public StreamedContent Publicaciones(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM publicaciones WHERE usuarios_id_usuarios=? and id_publicacion=?");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
		    while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		    }
			conexionBD.cerrar();
			return file;

		} catch (Exception e) {

		}

		return file;
	}

	public StreamedContent MiembroOrg(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM miembros_de_organizaciones WHERE usuarios_id_usuarios=? and id_miembro =?");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		    }
			conexionBD.cerrar();
			return file;

		} catch (Exception e) {

		}

		return file;
	}

	public StreamedContent AsesoriaTesis(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM asesorias_de_tesis WHERE usuarios_id_usuarios=? and id_asesorias_tesis=?");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		    }
			conexionBD.cerrar();
			return file;

		} catch (Exception e) {

		}

		return file;
	}

	public StreamedContent Sinodal(int id, int num) {
		StreamedContent file = null;
		ResultSet rs;
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement st = connection.prepareStatement("SELECT evidencia FROM sinodales WHERE usuarios_id_usuarios=? and id_sinodal=?");
			st.setInt(1, id);
			st.setInt(2, num);
            rs = st.executeQuery();
			while (rs.next()) {
				InputStream stream = rs.getBinaryStream(1);
                file = new DefaultStreamedContent(stream, "image/jpg", "evidencia.jpg");
		    }
			conexionBD.cerrar();
			return file;

		} catch (Exception e) {

		}

		return file;
	}

	

}
