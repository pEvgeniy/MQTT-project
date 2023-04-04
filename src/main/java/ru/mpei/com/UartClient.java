package ru.mpei.com;

import com.fazecast.jSerialComm.SerialPort;
import ru.mpei.mqtt.PublishSample;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class UartClient {

    private SerialPort port;
    private PublishSample publishSample = new PublishSample();

    public void start(String portName, int boundRate) {
        stop();

        port = SerialPort.getCommPort(portName);
        port.setBaudRate(boundRate);
        port.openPort();

        OutputStream outputStream = port.getOutputStream();

//        publishSample.Publish(port.getOutputStream().toString());

        System.out.println("Successfully started UartClient");

//        writer = new PrintWriter(new PrintStream(outputStream), true);
//        log.info("Successfully started UartClient");
    }

    public void stop() {
        if (port != null && port.isOpen()) {
            port.closePort();
        }
    }
}
