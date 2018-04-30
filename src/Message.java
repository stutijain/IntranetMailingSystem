
public class Message {
String to;
String msg;
String from;
public static int count=0;
public static String s;
public Message(String to, String msg, String from)
{	count++;
	this.to=to;
	this.msg=msg;
	this.from=from;
}
}
