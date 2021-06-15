package com.messagecontrol;

import org.springframework.stereotype.Component;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;


@Component
public class ActiveMQhandler {

    private static String MQEndpoint;
    private Session session;
    private Connection conn;

    public void setMQEndpoint(String MQEndpoint) {

        this.MQEndpoint = MQEndpoint;
    }

//    @Autowired
//    public ActiveMQhandler() {
//        this.MQEndpoint = MQEndpoint;
//    }

    private void startSession(){
        try {
            String url = MQEndpoint;
            // Create a Connection via ConnectionFactory
            ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
            conn = connFactory.createConnection();
            conn.start();

            // Create a Session
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        }
        catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    private void closeSession() {
        try {
        session.close();
        conn.close();
        }
        catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    // generic message sender, takes any object
    public void sendMessage(Object object, String queue){

        // start session
        startSession();
        // Set the queue
        try{
            Destination destination = session.createQueue(queue);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Send msg to queue
            javax.jms.ObjectMessage obj = session.createObjectMessage((Serializable)object);
            producer.send(obj);

            // close session
            closeSession();
        }
        catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

}
