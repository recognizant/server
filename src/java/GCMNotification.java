

import Database.DBQuery;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/GCMNotification")
public class GCMNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;
PrintWriter out;
	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "AIzaSyB5YLTF-a2D3x7F_1OaFU1k38EKGCbN0Qo";//AIzaSyA9DQTcggUtfqABClnV_XYZVE8QiKBEaP4
	static final String REGISTER_NAME = "name";
	static final String MESSAGE_KEY = "message";
	static final String TO_NAME = "toName";
	static final String REG_ID_STORE = "E://GCMRegId.txt";

        
        
        
	public GCMNotification() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
out=response.getWriter();
        
		String action = request.getParameter("action");
            System.out.println("action..>"+action);
		if ("shareRegId".equalsIgnoreCase(action)) {
                    System.out.println("iffffffffffff");
                    System.out.println("................."+request.getParameter("name"));
                    System.out.println("................."+request.getParameter("regId"));
			//writeToFile(request.getParameter("name"),request.getParameter("regId"));
                        
                        
                        write(request.getParameter("name"),
					request.getParameter("regId"));
			request.setAttribute("pushStatus",
					"GCM Name and corresponding RegId Received.");
//			request.getRequestDispatcher("index.jsp")
//					.forward(request, response);

		} else if ("sendMessage".equalsIgnoreCase(action)) {
                    System.out.println("elseeeeeeeeee");
			try {
				String fromName = request.getParameter(REGISTER_NAME);
				String toName = request.getParameter(TO_NAME);
				String userMessage = request.getParameter(MESSAGE_KEY);
                            //    DBQuery dbq=new DBQuery();
                            //    dbq.addMessage(fromName, toName, userMessage);
                                
                                userMessage="msg-"+userMessage;
                                
                                
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
                                System.out.println(fromName+"====="+toName+"========"+userMessage);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage)
						.addData(REGISTER_NAME, fromName).build();
				Map<String, String> regIdMap = read();//readFromFile();
				String regId = regIdMap.get(toName);
                                System.out.println(message+"---"+regId);
				Result result = sender.send(message, regId, 1);//    regId    "1047500615018"
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
                        out.print("ok");
//			request.getRequestDispatcher("index.jsp")
//					.forward(request, response);
		}else if ("friendReq".equalsIgnoreCase(action)) {
                    System.out.println("elseeeeeeeeee");
			try {
				String fromName = request.getParameter(REGISTER_NAME);
				String toName = request.getParameter(TO_NAME);
				String userMessage = request.getParameter(MESSAGE_KEY);
                            //    DBQuery dbq=new DBQuery();
                            //    dbq.addMessage(fromName, toName, userMessage);
                                
                                userMessage="friendreq-"+userMessage;
                                
                                
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
                                System.out.println(fromName+"====="+toName+"========"+userMessage);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage)
						.addData(REGISTER_NAME, fromName).build();
				Map<String, String> regIdMap = read();//readFromFile();
				String regId = regIdMap.get(toName);
                                System.out.println(message+"---"+regId);
				Result result = sender.send(message, regId, 1);//    regId    "1047500615018"
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
                        out.print("ok");
//			request.getRequestDispatcher("index.jsp")
//					.forward(request, response);
		}  
                
                else if ("multicast".equalsIgnoreCase(action)) {

			try {
				String fromName = request.getParameter(REGISTER_NAME);
				String userMessage = request.getParameter(MESSAGE_KEY);
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage)
						.addData(REGISTER_NAME, fromName).build();
				Map<String, String> regIdMap = readFromFile();

				List<String> regIdList = new ArrayList<String>();

				for (Entry<String, String> entry : regIdMap.entrySet()) {
					regIdList.add(entry.getValue());
				}

				MulticastResult multiResult = sender
						.send(message, regIdList, 1);
				request.setAttribute("pushStatus", multiResult.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}
	}

	private void writeToFile(String name, String regId) throws IOException {
            try{
		Map<String, String> regIdMap = readFromFile();
		regIdMap.put(name, regId);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				REG_ID_STORE, false)));
		for (Map.Entry<String, String> entry : regIdMap.entrySet()) {
			out.println(entry.getKey() + "," + entry.getValue());
		}
		out.println(name + "," + regId);
		out.close();
            }catch(Exception e){e.printStackTrace();}
	}

	private Map<String, String> readFromFile() throws IOException {
            Map<String, String> regIdMap=null;
            try{
		BufferedReader br = new BufferedReader(new FileReader(REG_ID_STORE));
		String regIdLine = "";
	          regIdMap = new HashMap<String, String>();
		while ((regIdLine = br.readLine()) != null) {
                    System.out.println(">>>"+regIdLine);
			String[] regArr = regIdLine.split(",");
			regIdMap.put(regArr[0], regArr[1]);
		}
		br.close();
		
            }catch(Exception e){e.printStackTrace();}
            return regIdMap;
	}
        
        
        public Map<String, String> read() throws ClassNotFoundException, SQLException{
        
          Map<String, String> regIdMap=null;
         regIdMap = new HashMap<String, String>();
        
         DBQuery db=new DBQuery();
         ResultSet rs=db.readRegID();
         
         while(rs.next())
         {
          regIdMap.put(rs.getString("user"),rs.getString("reg_id"));
         
         }
         
        
         return regIdMap;
        }
        
        private void write(String name, String regId) throws IOException {
            try{
		Map<String, String> regIdMap = read();
		regIdMap.put(name, regId);
                
                DBQuery db=new DBQuery();
                db.updateRegId(name, regId);
                
                
                
                
                
//		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
//				REG_ID_STORE, false)));
//		for (Map.Entry<String, String> entry : regIdMap.entrySet()) {
//			out.println(entry.getKey() + "," + entry.getValue());
//		}
		out.println(name + "," + regId);
		out.close();
            }catch(Exception e){e.printStackTrace();}
	}
}
