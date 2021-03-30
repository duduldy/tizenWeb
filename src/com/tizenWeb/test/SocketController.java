package com.tizenWeb.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
// WebSocket의 호스트 주소 설정
@ServerEndpoint("/socket")
public class SocketController {
	
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen() {
		// 콘솔에 접속 로그를 출력한다.
		System.out.println("client is now connected...");
	}
	
	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public String handleMessage(String message) {
		// 메시지 내용을 콘솔에 출력한다.
		System.out.println("receive from client : " + message);

		
		// 데이터 저장 (파일 생성)
		// message format : fileName(숫자),usrID,timeStamp,x,y,z
		String fileName = message.split(",")[0];
		int subFlag = message.indexOf(",");
		String getMsg = message.substring(subFlag+1); // 1번째 , 다음부터
		
		// 파일 생성
		String dirPath = "C:\\dataLog\\" ;
		fileWriter(dirPath, "log_"+fileName+".txt", getMsg);
		
		// 메시지를 작성한다.
		String timeStamp = message.split(",")[3]; // timeStamp / 커넥션 구분
		//String replymessage = "logOk";
		String replymessage = timeStamp;
		// 메시지를 콘솔에 출력한다.
		//System.out.println("send to client : "+replymessage);
		// 메시지를 브라우저에 보낸다.
		return replymessage;
	}
	
	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose() {
		// 콘솔에 접속 끊김 로그를 출력한다.
		System.out.println("client is now disconnected...");
	}
	
	// WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
	@OnError
	public void handleError(Throwable t) {
		// 콘솔에 에러를 표시한다.
		t.printStackTrace();
	}
	
	
	// 파일 생성
	public void fileWriter(String dirPath, String fileName, String getMsg) {
		
        try{
        	// 해당 디렉토리가 없을경우 디렉토리를 생성
        	File Folder = new File(dirPath);
    		if (!Folder.exists()) {
				System.out.println("폴더 생성");
				Folder.mkdir(); //폴더 생성
			}
    		
        	/*
            // 파일 객체 생성
            File file = new File(fileName) ;
             
            // true 지정시 파일의 기존 내용에 이어서 작성
            FileWriter fw = new FileWriter(file, true) ;
            */
        	
            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상/50K를 넘을때 성능차이 남)
            BufferedWriter fw = new BufferedWriter(new FileWriter(dirPath + fileName, true));
             
            // 파일안에 문자열 쓰기
            String newline = System.getProperty("line.separator"); // windows : \r\n, linux : \n
            fw.write(getMsg+newline);
            fw.flush();
 
            // 객체 닫기
            fw.close();
             
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}