package com.eightdevelopers.sicva.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.BibliografiasDTO;
import com.eightdevelopers.sicva.dto.BloquesDTO;
import com.eightdevelopers.sicva.dto.DprincipalesDTO;
import com.eightdevelopers.sicva.dto.MateriasDTO;
import com.eightdevelopers.sicva.dto.ProgramaDTO;
import com.eightdevelopers.sicva.dto.PropositosDTO;






// GUARDAR

public class ProgramaDAO {
	public String guardarPrograma(ProgramaDTO programasDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_programa(?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, programasDTO.getIdusuario());
				callableStatement.setInt(3, programasDTO.getIdmateria());
		

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "Se ha guardado satisfactoriamente";

				} else {
					return "";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "Se ha guardado satisfactoriamente";

	}
/* A QUI EMPIZA CRUD DE DATOS PRINCIPALES----------------------------------------
*/	
	///////guardar  DATOS RINCIPALES DEL BLOQUE
	
	
	public String guardarDprincipal(ProgramaDTO dprincipalesDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_dprincipales(?,?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, dprincipalesDTO.getNivel());
				callableStatement.setString(3, dprincipalesDTO.getClave());
				callableStatement.setString(4, dprincipalesDTO.getSeriacion());
				
			//	callableStatement.setString(3, asistenciacursosDTO.getFechatitulacion());

				DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

				Date date = (Date) formatter.parse(dprincipalesDTO.getFechaelaboracion());

				System.out.println(date);

				Calendar cal = Calendar.getInstance();

				cal.setTime(date);

				String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DATE);
				
					

				System.out.println("formatedDate : " + formatedDate);

				callableStatement.setString(5, formatedDate);
				///////////////////////////////////////////////////////////////////////////
				DateFormat formatter2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

				Date date2 = (Date) formatter2.parse(dprincipalesDTO.getFechaactualizacion());

				System.out.println(date2);

				Calendar cal2 = Calendar.getInstance();

				cal.setTime(date2);

				String formatedDate2 = cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-"
						+ cal2.get(Calendar.DATE);
				
					

				System.out.println("formatedDate : " + formatedDate2);
				callableStatement.setString(6, formatedDate2);
				
				callableStatement.setInt(7, dprincipalesDTO.getHorasteoricas());
				callableStatement.setInt(8, dprincipalesDTO.getHoraspracticas());
				callableStatement.setInt(9, dprincipalesDTO.getHorasindependientes());
				callableStatement.setInt(10, dprincipalesDTO.getTotalhoras());
				callableStatement.setInt(11, dprincipalesDTO.getCreditos());
			
				callableStatement.setInt(12, dprincipalesDTO.getIdprogramaprincipal());
				
				
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "Se ha guardado satisfactoriamente";

				} else {
					return "";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "Se ha guardado satisfactoriamente";

	}

	/////////////////////777
	
	//listar programa
	
	///////////////////////////
	
	
	public List<ProgramaDTO> listardprincipales(Integer id) {

		try {

			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM datos_principales  WHERE idprograma_analitico =?");
			ps.setInt(1, id);
			
			List<ProgramaDTO> listado = new ArrayList<	ProgramaDTO>();
			ResultSet rs = ps.executeQuery();
			System.out.println("ENTROOOOOOOOOOOOOOOOOO");
			boolean found = false;

			while (rs.next()) {
				ProgramaDTO DTOp = new ProgramaDTO();

			    DTOp.setIddatos(rs.getInt("iddatos_principales"));
				DTOp.setNivel(rs.getString("nivel_educativo"));
			    DTOp.setClave(rs.getString("clave_unidad"));
				DTOp.setSeriacion(rs.getString("seriacion"));
				DTOp.setFechaelaboracion(rs.getString("fecha_elaboracion"));
				DTOp.setFechaactualizacion(rs.getString("fecha_actualizacion"));
				DTOp.setHorasteoricas(rs.getInt("horas_teoricas"));
				DTOp.setHoraspracticas(rs.getInt("horas_practicas"));
			    DTOp.setHorasindependientes(rs.getInt("horas_independientes"));
				DTOp.setTotalhoras(rs.getInt("total_horas"));
				DTOp.setCreditos(rs.getInt("creditos"));
				DTOp.setIdprogramaprincipal(rs.getInt("idprograma_analitico"));
			
				
				listado.add(DTOp);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarDATOSPRINCIPALES()controler programa" + e.getMessage());
			return null;
		}

	}
	
	
	//////////////////77
	
	
	public String ActualizarDprincipales(ProgramaDTO DTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_dprincipales(?,?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2,DTO.getIddatos());
				callableStatement.setString(3, DTO.getNivel());
				callableStatement.setString(4, DTO.getClave());
				callableStatement.setString(5, DTO.getSeriacion());
				callableStatement.setString(6, DTO.getFechaelaboracion());
				callableStatement.setString(7, DTO.getFechaactualizacion());
				callableStatement.setInt(8, DTO.getHorasteoricas());
				callableStatement.setInt(9, DTO.getHoraspracticas());
				callableStatement.setInt(10, DTO.getHorasindependientes());
				callableStatement.setInt(11, DTO.getTotalhoras());
				callableStatement.setInt(12, DTO.getCreditos());


				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "Se ha Actualizo satisfactoriamente";
				} else {
					return "";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "";

	}

	
	
	/////////////////////////////77
	// ELIMINAR
		public String EliminarDprincipales(ProgramaDTO DTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();
					//System.out.println("eliminadolllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
					String sql = "{ ? = call eliminar_dprincipales(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, DTO.getIddatos());
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha eliminado satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}

/*Aqui TERMINA CRUD DATOS PRINCIPALAES----------------------------------------------------------------------------------------------*/
	
	
		/*BLOQUES----------------------------------------------------------------------------------------------*/
		/////guardar
		
		
		public String guardarBloque(ProgramaDTO bloquesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_bloque(?,?,?,?,?,?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, 1);
					
					
				//	callableStatement.setString(3, asistenciacursosDTO.getFechatitulacion());

					DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date = (Date) formatter.parse(bloquesDTO.getFechai());

					System.out.println(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(date);

					String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
							+ cal.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate);

					callableStatement.setString(3, formatedDate);
					///////////////////////////////////////////////////////////////////////////
					DateFormat formatter2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date2 = (Date) formatter2.parse(bloquesDTO.getFechaf());

					System.out.println(date2);

					Calendar cal2 = Calendar.getInstance();

					cal.setTime(date2);

					String formatedDate2 = cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-"
							+ cal2.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate2);
					callableStatement.setString(4, formatedDate2);
					////////////////////////////////////////////////////////7
					DateFormat formatter3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date3 = (Date) formatter3.parse(bloquesDTO.getFechae());

					System.out.println(date3);

					Calendar cal3 = Calendar.getInstance();

					cal.setTime(date3);

					String formatedDate3 = cal3.get(Calendar.YEAR) + "-" + (cal3.get(Calendar.MONTH) + 1) + "-"
							+ cal2.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate3);
					callableStatement.setString(5, formatedDate3);
					
					
					/////////////////////////////77777
					callableStatement.setString(6, bloquesDTO.getTemas());
					callableStatement.setInt(7,  bloquesDTO.getHorasestimadas());
					callableStatement.setString(8,  bloquesDTO.getSiatuaciones());
					callableStatement.setString(9,  bloquesDTO.getRecursos());
					callableStatement.setString(10,  bloquesDTO.getEstrategias());
					callableStatement.setInt(11, bloquesDTO.getIdproB());
				
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "Se ha guardado satisfactoriamente";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.. guardar bloque1" + ex.getMessage());
			}

			return "Se ha guardado satisfactoriamente";

		}
		
		
		
		///////////////////////////////////////////////guardar bloque 2
		
		public String guardarBloque2(ProgramaDTO bloquesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_bloque(?,?,?,?,?,?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, 2);
					
					
				//	callableStatement.setString(3, asistenciacursosDTO.getFechatitulacion());

					DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date = (Date) formatter.parse(bloquesDTO.getFechai());

					System.out.println(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(date);

					String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
							+ cal.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate);

					callableStatement.setString(3, formatedDate);
					///////////////////////////////////////////////////////////////////////////
					DateFormat formatter2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date2 = (Date) formatter2.parse(bloquesDTO.getFechaf());

					System.out.println(date2);

					Calendar cal2 = Calendar.getInstance();

					cal.setTime(date2);

					String formatedDate2 = cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-"
							+ cal2.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate2);
					callableStatement.setString(4, formatedDate2);
					////////////////////////////////////////////////////////7
					DateFormat formatter3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date3 = (Date) formatter3.parse(bloquesDTO.getFechae());

					System.out.println(date3);

					Calendar cal3 = Calendar.getInstance();

					cal.setTime(date3);

					String formatedDate3 = cal3.get(Calendar.YEAR) + "-" + (cal3.get(Calendar.MONTH) + 1) + "-"
							+ cal2.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate3);
					callableStatement.setString(5, formatedDate3);
					
					
					/////////////////////////////77777
					callableStatement.setString(6, bloquesDTO.getTemas());
					callableStatement.setInt(7,  bloquesDTO.getHorasestimadas());
					callableStatement.setString(8,  bloquesDTO.getSiatuaciones());
					callableStatement.setString(9,  bloquesDTO.getRecursos());
					callableStatement.setString(10,  bloquesDTO.getEstrategias());
					callableStatement.setInt(11, bloquesDTO.getIdproB());
				
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "Se ha guardado satisfactoriamente";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.. guardar bloque1" + ex.getMessage());
			}

			return "Se ha guardado satisfactoriamente";

		}
		
		///////////////////////////////////////77
		
		
		///////////////////////GUARDAR BLOQUE 3
		public String guardarBloque3(ProgramaDTO bloquesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_bloque(?,?,?,?,?,?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, 3);
					
					
				//	callableStatement.setString(3, asistenciacursosDTO.getFechatitulacion());

					DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date = (Date) formatter.parse(bloquesDTO.getFechai());

					System.out.println(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(date);

					String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
							+ cal.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate);

					callableStatement.setString(3, formatedDate);
					///////////////////////////////////////////////////////////////////////////
					DateFormat formatter2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date2 = (Date) formatter2.parse(bloquesDTO.getFechaf());

					System.out.println(date2);

					Calendar cal2 = Calendar.getInstance();

					cal.setTime(date2);

					String formatedDate2 = cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-"
							+ cal2.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate2);
					callableStatement.setString(4, formatedDate2);
					////////////////////////////////////////////////////////7
					DateFormat formatter3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

					Date date3 = (Date) formatter3.parse(bloquesDTO.getFechae());

					System.out.println(date3);

					Calendar cal3 = Calendar.getInstance();

					cal.setTime(date3);

					String formatedDate3 = cal3.get(Calendar.YEAR) + "-" + (cal3.get(Calendar.MONTH) + 1) + "-"
							+ cal2.get(Calendar.DATE);
					
						

					System.out.println("formatedDate : " + formatedDate3);
					callableStatement.setString(5, formatedDate3);
					
					
					/////////////////////////////77777
					callableStatement.setString(6, bloquesDTO.getTemas());
					callableStatement.setInt(7,  bloquesDTO.getHorasestimadas());
					callableStatement.setString(8,  bloquesDTO.getSiatuaciones());
					callableStatement.setString(9,  bloquesDTO.getRecursos());
					callableStatement.setString(10,  bloquesDTO.getEstrategias());
					callableStatement.setInt(11, bloquesDTO.getIdproB());
				
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "Se ha guardado satisfactoriamente";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.. guardar bloque1" + ex.getMessage());
			}

			return "Se ha guardado satisfactoriamente";

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		////////////
		
		
		
	/////////listar BLOQUE  
		
		public List<ProgramaDTO> listarbloques(Integer id) {

			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM bloques  WHERE  numero_bloque=1 and idprograma_analitico =?");
				ps.setInt(1, id);
				
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
				ResultSet rs = ps.executeQuery();
				System.out.println("ENTROOOOOOOOOOOOOOOOOO");
				boolean found = false;

				while (rs.next()) {
					ProgramaDTO DTO = new ProgramaDTO();

				    DTO.setIdbloque(rs.getInt("idbloque1"));
					DTO.setNumerobloque(rs.getInt("numero_bloque"));
				    DTO.setFechai(rs.getString("fechaI"));
					DTO.setFechaf(rs.getString("fechaF"));
					DTO.setFechae(rs.getString("fechaE"));
					DTO.setTemas(rs.getString("temas"));
					DTO.setHorasestimadas(rs.getInt("horas_estimadas"));
					DTO.setSiatuaciones(rs.getString("situaciones_aprendizaje"));
					DTO.setRecursos(rs.getString("recursos"));
				    DTO.setEstrategias(rs.getString("estrategias_evaluacion"));
				    DTO.setIdproB(rs.getInt("idprograma_analitico"));
				
					
					listado.add(DTO);

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarBloque()" + e.getMessage());
				return null;
			}

		}
		
		
		///////////LISTAR BLOQUE 2
		
		public List<ProgramaDTO> listarbloques2(Integer id) {

			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM bloques  WHERE  numero_bloque=2 and idprograma_analitico =?");
				ps.setInt(1, id);
				
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
				ResultSet rs = ps.executeQuery();
				System.out.println("ENTROOOOOOOOOOOOOOOOOO");
				boolean found = false;

				while (rs.next()) {
					ProgramaDTO DTO = new ProgramaDTO();

				    DTO.setIdbloque(rs.getInt("idbloque1"));
					DTO.setNumerobloque(rs.getInt("numero_bloque"));
				    DTO.setFechai(rs.getString("fechaI"));
					DTO.setFechaf(rs.getString("fechaF"));
					DTO.setFechae(rs.getString("fechaE"));
					DTO.setTemas(rs.getString("temas"));
					DTO.setHorasestimadas(rs.getInt("horas_estimadas"));
					DTO.setSiatuaciones(rs.getString("situaciones_aprendizaje"));
					DTO.setRecursos(rs.getString("recursos"));
				    DTO.setEstrategias(rs.getString("estrategias_evaluacion"));
				    DTO.setIdproB(rs.getInt("idprograma_analitico"));
				
					
					listado.add(DTO);

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarBloque()" + e.getMessage());
				return null;
			}

		}
		
		////////////////////LISTAR BLOQUES 3
		
		public List<ProgramaDTO> listarbloques3(Integer id) {

			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM bloques  WHERE  numero_bloque=3 and idprograma_analitico =?");
				ps.setInt(1, id);
				
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
				ResultSet rs = ps.executeQuery();
				System.out.println("ENTROOOOOOOOOOOOOOOOOO");
				boolean found = false;

				while (rs.next()) {
					ProgramaDTO DTO = new ProgramaDTO();

				    DTO.setIdbloque(rs.getInt("idbloque1"));
					DTO.setNumerobloque(rs.getInt("numero_bloque"));
				    DTO.setFechai(rs.getString("fechaI"));
					DTO.setFechaf(rs.getString("fechaF"));
					DTO.setFechae(rs.getString("fechaE"));
					DTO.setTemas(rs.getString("temas"));
					DTO.setHorasestimadas(rs.getInt("horas_estimadas"));
					DTO.setSiatuaciones(rs.getString("situaciones_aprendizaje"));
					DTO.setRecursos(rs.getString("recursos"));
				    DTO.setEstrategias(rs.getString("estrategias_evaluacion"));
				    DTO.setIdproB(rs.getInt("idprograma_analitico"));
				
					
					listado.add(DTO);

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarBloque()" + e.getMessage());
				return null;
			}

		}
		
		
		
		///////////
	
		////////////////////////Actualizar todos los BLOQUESSSSSSSSSS
		
		public String ActualizarBloque(ProgramaDTO DTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					
					System.out.println("entro actualizar");
					String sql = "{ ? = call actualizar_bloque(?,?,?,?,?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2,DTO.getIdbloque());
		
					callableStatement.setString(3, DTO.getFechai());
					callableStatement.setString(4, DTO.getFechaf());
					callableStatement.setString(5, DTO.getFechae());
					callableStatement.setString(6, DTO.getTemas());
					callableStatement.setInt(7, DTO.getHorasestimadas());
					callableStatement.setString(8, DTO.getSiatuaciones());
					callableStatement.setString(9, DTO.getRecursos());
					System.out.println("entro actualizar-------"+DTO.getRecursos());
					callableStatement.setString(10, DTO.getEstrategias());
			
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha Actualizo satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}
	////////////////777777777
		
		//////////eliminar bloque
		
		
	
		public String EliminarBloque(ProgramaDTO DTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();
					System.out.println("eliminadolllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
					String sql = "{ ? = call eliminar_bloque(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, DTO.getIdbloque());
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha eliminado satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.. Eliminar" + ex.getMessage());
			}

			return "";

		}
	
		
		
		//////////////////////////crud Unidades 
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		public String guardarUbicacion(ProgramaDTO ubicacionesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_ubicacion(?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setString(2, ubicacionesDTO.getCampo());
					callableStatement.setString(3, ubicacionesDTO.getProblema());
					callableStatement.setString(4, ubicacionesDTO.getCompetencias());
					callableStatement.setString(5, ubicacionesDTO.getProposito());
					callableStatement.setInt(6,ubicacionesDTO.getIdprogramaubica());
				 

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "¡Se guardado satisfactoriamente!";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}
		
		
		public List<ProgramaDTO> listarubicaciones(Integer id) {

			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM ubicacion_unidad  WHERE idprograma_analitico =?");
				ps.setInt(1, id);
				
								
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();

				ResultSet rs = ps.executeQuery();

				boolean found = false;

				while (rs.next()) {
					ProgramaDTO ubicacionesDTO = new ProgramaDTO();

					ubicacionesDTO.setIdunidad(rs.getInt("idubicacion_unidad"));
					ubicacionesDTO.setCampo(rs.getString("campo_formativo"));
					ubicacionesDTO.setProblema(rs.getString("problema_eje"));
					ubicacionesDTO.setCompetencias(rs.getString("competencias"));
					ubicacionesDTO.setProposito(rs.getString("proposito"));
					ubicacionesDTO.setIdprogramaubica(rs.getInt("idprograma_analitico"));
					
					listado.add(ubicacionesDTO);

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarcomisiones()" + e.getMessage());
				return null;
			}

		}
		
		public String EliminarUbicaciones(ProgramaDTO ubicacionesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call eliminar_ubicacion(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, ubicacionesDTO.getIdunidad());
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "¡Se elimino satisfactoriamente!";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}
		
		
		
		public String ActualizarUbicaciones(ProgramaDTO ubicacionesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call actualizar_ubicacion(?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
	            	callableStatement.setInt(2, ubicacionesDTO.getIdunidad());
	            	
					callableStatement.setString(3, ubicacionesDTO.getCampo());
				 	
					callableStatement.setString(4, ubicacionesDTO.getProblema());
				
					callableStatement.setString(5, ubicacionesDTO.getCompetencias());
					
					callableStatement.setString(6, ubicacionesDTO.getProposito());
					

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "¡Se actualizo satisfactoriamente!";
					} else {
						return "";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}
			return "";
		}

		
		//////////////////////finaliza crud unidades
		
		////////////inicia crud Propositos 77777777777777	////////////////////////////////////////////
		
		
		
		public String guardarPropositos(ProgramaDTO propositosDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_propositos_especificos(?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setString(2, propositosDTO.getDeclarativo());
					callableStatement.setString(3, propositosDTO.getProcedimental());
					callableStatement.setString(4, propositosDTO.getValoral());
					callableStatement.setInt(5,  propositosDTO.getIdprogramapro());
				

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "Se ha guardado satisfactoriamente";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "Se ha guardado satisfactoriamente";

		}
		
		////////////listar propositos
		
		
		
		public List<ProgramaDTO> listarpropositos(Integer id) {

			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM propositos_especificos  WHERE idprograma_analitico =?");
				ps.setInt(1, id);
				
				
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
				ResultSet rs = ps.executeQuery();

				boolean found = false;

				while (rs.next()) {
					ProgramaDTO propositosDTO = new ProgramaDTO();

					propositosDTO.setIdproposito(rs.getInt("idpropositos_especificos"));
					propositosDTO.setDeclarativo(rs.getString("adeclarativo"));
					propositosDTO.setProcedimental(rs.getString("aprocedimental"));
					propositosDTO.setValoral(rs.getString("avaloral"));
					propositosDTO.setIdprogramapro(rs.getInt("idprograma_analitico"));
						
					listado.add(propositosDTO);

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarpropositos()" + e.getMessage());
				return null;
			}

		}
		
		
		
		
		
		public String EliminarPropositos(ProgramaDTO propositosDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call eliminar_propositos_especificos(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, propositosDTO.getIdproposito());

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha eliminado satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}
		
		public String ActualizarPropositos(ProgramaDTO propositosDTO) {
			
			
			
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call actualizar_propositos_especificos(?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, propositosDTO.getIdproposito());
					callableStatement.setString(3, propositosDTO.getDeclarativo());
					callableStatement.setString(4, propositosDTO.getProcedimental());
					callableStatement.setString(5,propositosDTO.getValoral());
				

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha Actualizo satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}

		
		///////////////termina CRUD PROPOSITOS ESPECIFICO
		
		///////////////////////////////////////////////////////CRUD CRITERIOS FINALES/////////////////////////////////////////7777
		
		
		public String guardacriterio(ProgramaDTO criteriosDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_criterios(?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setString(2, criteriosDTO.getDescripcioncriterio());
									callableStatement.setInt(3,  criteriosDTO.getIdprogramacriterio());


					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "Se ha guardado satisfactoriamente";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "Se ha guardado satisfactoriamente";

		}

		
		
		public List<ProgramaDTO> listarcriterios(Integer id) {

			try {
		

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM criterios_finales  WHERE idprograma_analitico =?");
				ps.setInt(1, id);
				
				
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
				ResultSet rs = ps.executeQuery();

				boolean found = false;

				while (rs.next()) {
					ProgramaDTO propositosDTO = new ProgramaDTO();

					propositosDTO.setIdcriterio(rs.getInt("idcriterios_finales"));
					propositosDTO.setDescripcioncriterio(rs.getString("descripcion"));
		
					propositosDTO.setIdprogramacriterio(rs.getInt("idprograma_analitico"));
						
					listado.add(propositosDTO);

					found = true;

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarunidad()" + e.getMessage());
				return null;
			}

		}

		
		
		public String EliminarCriterios(ProgramaDTO criteriosDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call eliminar_criterios(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, criteriosDTO.getIdcriterio());
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha eliminado satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}

		
		public String ActualizarCriterios(ProgramaDTO criteriosDTO) {
			
			
			
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call actualizar_criterios(?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, criteriosDTO.getIdcriterio());
					callableStatement.setString(3, criteriosDTO.getDescripcioncriterio());
					
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha Actualizo satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}

		///////////////////////termina crud criterios 
		
		//////////////////CRUD BILIOGRAFIAS
		
		public String guardarBibliografias(ProgramaDTO bibliografiasDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call insertar_bibliografias(?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setString(2, bibliografiasDTO.getBibliografia());
					callableStatement.setString(3, bibliografiasDTO.getReferencia());
					callableStatement.setInt(4, bibliografiasDTO.getIdprogramablibli());

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {

						return "Se ha guardado satisfactoriamente";

					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "Se ha guardado satisfactoriamente";

		}

		///////listar bibliografias
		
		
		public List<ProgramaDTO> listarbibliografias(Integer id) {

			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM bibliografias  WHERE idprograma_analitico =?");
				ps.setInt(1, id);
				
				List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
				ResultSet rs = ps.executeQuery();

				boolean found = false;

				while (rs.next()) {
					ProgramaDTO bibliografiasDTO = new ProgramaDTO();

					bibliografiasDTO.setIdbibli(rs.getInt("idbibliografias"));
					bibliografiasDTO.setBibliografia(rs.getString("bibliografia"));
					bibliografiasDTO.setReferencia(rs.getString("referencia"));
					bibliografiasDTO.setIdprogramablibli(rs.getInt("idprograma_analitico"));
;
					
					listado.add(bibliografiasDTO);

					found = true;

				}
				rs.close();
				if (found) {
					return listado;
				} else {

					return null;
				}

			} catch (Exception e) {
				System.out.println("Error en listarbibliografia()" + e.getMessage());
				return null;
			}

		}
		
		/////////eliminar bibliografias
		
		
		public String EliminarBibliografias(ProgramaDTO bibliografiasDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call eliminar_bibliografias(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, bibliografiasDTO.getIdbibli());
					
					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha eliminado satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}
		
		
		/////actualizart bibliografia 
		
		public String ActualizarBibliografias(ProgramaDTO bibliografiasDTO) {
			
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call actualizar_bibliografias(?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, bibliografiasDTO.getIdbibli());
					callableStatement.setString(3, bibliografiasDTO.getBibliografia());
					callableStatement.setString(4, bibliografiasDTO.getReferencia());
		

					callableStatement.execute();
					Integer num = callableStatement.getInt(1);
					connection.close();
					if (num == 1) {
						return "Se ha Actualizo satisfactoriamente";
					} else {
						return "";

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				System.out.println("Error en.." + ex.getMessage());
			}

			return "";

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	// LISTAR PROGRAMAS ANALITICOS GENERADOS
	/**
	 * 
	 * @return
	 */
	public List<ProgramaDTO> listarprograma(Integer id) {

		try {

			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(" SELECT p.*,m.nombre FROM vinculacion_materia b INNER JOIN materias m ON b.materias_id_materia=m.id_materia   INNER JOIN programa_analitico p ON p.id_vinculacion_materia=b.id_vinculacion WHERE p.id_usuario=?");
			ps.setInt(1, id);
			
			
			
			List<ProgramaDTO> listado = new ArrayList<ProgramaDTO>();
//
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				ProgramaDTO materiasDTO = new ProgramaDTO();

				materiasDTO.setIdprograma(rs.getInt("idprograma_analitico"));
				materiasDTO.setIdusuario(rs.getInt("id_usuario"));
				materiasDTO.setIdmateria(rs.getInt("id_vinculacion_materia"));
				materiasDTO.setDescripcionmateriapro(rs.getString("m.nombre"));
				
				
				
				listado.add(materiasDTO);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarmaterias()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarMaterias(MateriasDTO materiasDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_materias(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, materiasDTO.getIdmaterias());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "Se ha eliminado satisfactoriamente";
				} else {
					return "No se pudo eliminar";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR

	public String ActualizarMaterias(MateriasDTO materiasDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_materias(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, materiasDTO.getIdmaterias());
				callableStatement.setInt(3, materiasDTO.getSemestre());
				callableStatement.setString(4, materiasDTO.getDescripcion());
				callableStatement.setInt(5,1);
				

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "Se ha Actualizo satisfactoriamente";
				} else {
					return "No se pudo Actualizar por duplicado";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "";

	
	}
}
