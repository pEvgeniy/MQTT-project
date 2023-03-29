package ru.mpei.com;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Reader {
    private static final SerialPort serialPort = new SerialPort("COM3");

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            try {
                serialPort.openPort();
                serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); /*Задаем основные параметры протокола UART*/
                serialPort.setEventsMask(SerialPort.MASK_RXCHAR); /*Устанавливаем маску или список события на которые будет происходить реакция. В данном случае это приход данных в буффер порта*/
                serialPort.addEventListener(new Listener());
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
}
