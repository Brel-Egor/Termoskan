import java.util.ArrayList;

import org.bridj.Platform;

import jssc.*;

public class SerialPort_communicator {
private  SerialPort serialPort;
private ArrayList<Double> temperature= new ArrayList<Double>();
private String[] portNames;
private StringBuilder message = new StringBuilder();
SerialPort_communicator()
{
	portNames = SerialPortList.getPortNames();
	
}

public ArrayList<Double> get_temp()
{
	return temperature;
}
public String[] return_port()
{
	return portNames;
}

public void Send(char x) throws SerialPortException
{
	serialPort.writeByte((byte) x);
	
}

public void close() throws SerialPortException
{
	serialPort.closePort();
}

public String readStr() throws SerialPortException
{
	return serialPort.readString();
}

public void open(String s) throws SerialPortException
{
	
	serialPort=new SerialPort(s);
	serialPort.openPort();
	serialPort.setParams(SerialPort.BAUDRATE_9600,
	        SerialPort.DATABITS_8,
	        SerialPort.STOPBITS_1,
	        SerialPort.PARITY_NONE);
	serialPort.addEventListener(new SerialPortEventListener(){
		
		public void serialEvent(SerialPortEvent event) {
		    if(event.isRXCHAR() && event.getEventValue() > 0){
		        try {
		            byte buffer[] = serialPort.readBytes();
		            for (byte b: buffer) {
		                    if ( (b == '\r' || b == '\n') && message.length() > 0) {
		                        String toProcess = message.toString();
		                        temperature.add(Double.parseDouble(toProcess));	                        		                        
		                        message.setLength(0);
		                    }
		                    else {
		                        message.append((char)b);
		                    }
		            }                
		        }
		        catch (SerialPortException ex) {
		            System.out.println(ex);
		            System.out.println("serialEvent");
		        }
		    }
		}
	
	}, SerialPort.MASK_RXCHAR);
	
}

}
