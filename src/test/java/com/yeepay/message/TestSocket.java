package com.yeepay.message;


public class TestSocket {
	public static void main(String[] args) throws Exception{
		/*//开启socket连接
		InitClient.start();
		
		new Thread(new Runnable() {
			public void run() {
				while(true){
					byte[] rec = RecieveMessages.pop();
					String timeOutMsg = ErrorMessages.pop();
					if(rec != null){
						System.out.println("Rec:" + new String(rec));
					}
					if(timeOutMsg != null){
						System.out.println("TimeOut:" + timeOutMsg);
					}
				}
				
			}
		}).start();
		
		
		for(int i = 0; i < 20; i++){
			Message msg = new Message();
			msg.setMessageKey(String.valueOf(i));
			msg.setTimeOut(new Date(new Date().getTime() + 4000 * i));
			msg.setData("00011".getBytes());
			
			System.out.println("Send:" + i);
			SendMessages.push(msg);
			Thread.sleep(2000);
		}*/
	}
	
}
