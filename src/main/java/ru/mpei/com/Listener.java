package ru.mpei.com;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ru.mpei.mqtt.PublishSample;

public class Listener implements SerialPortEventListener {
    private static final SerialPort serialPort = new SerialPort("COM3");
    private final PublishSample publishSample = new PublishSample();

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) { /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
            try {
                String data = serialPort.readString(serialPortEvent.getEventValue()); /*Создаем строковую переменную  data, куда и сохраняем данные*/
                System.out.print(data);/*Выводим данные на консоль*/
                publishSample.Publish(data);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
}
