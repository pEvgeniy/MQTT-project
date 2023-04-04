package ru.mpei;

import com.fazecast.jSerialComm.SerialPort;
import ru.mpei.mqtt.PublishSample;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {

    private static byte[] bytes = new byte[3];

    private static PublishSample publishSample = new PublishSample();

    public static void main(String[] args) throws IOException {
        SerialPort sp = SerialPort.getCommPorts()[0];
//        sp.setComPortParameters(115200, 8, 1, 0);
        sp.setComPortParameters(115200, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        while (true) {

            InputStream stream = sp.getInputStream();

            stream.read(bytes);

            int num = (((bytes[0] & 0x01) << 9) | ((bytes[1] & 0xFF) << 1) | ((bytes[2] & 0x80) >> 7));

            if (num != 0) {
                System.out.println(num);
                publishSample.Publish("{\n\"value\": " + num + "\n}");
            }
        }
    }
}
//            sp.readBytes(bytes, 3);
//            InputStream is = sp.getInputStream();
//
//            is.read(bytes);
//
//            byte byte1 = bytes[0];
//            byte byte2 = bytes[1];
//            byte byte3 = bytes[2];
//
//            ByteBuffer buf1 = ByteBuffer.allocate(4);
//
//            buf1.put((byte) 0);
//            buf1.put(byte3);
//            buf1.put(byte2);
//            buf1.put(byte1);
//
//            buf1.rewind();
//
//            int i1 = buf1.getInt() >> 6;
//
//            if ((i1) != 0) {
//                System.out.println(i1);
//                publishSample.Publish("{\n\"value\": " + i1 + "\n}", "1");
//            }

//            sp.readBytes(bytes, 3);
