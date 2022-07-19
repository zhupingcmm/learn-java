package com.mf.active;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Active {
    public static void main(String[] args) {
        Destination destination = new ActiveMQTopic("test.topic");
        testDestination(destination);
    }

    public static void testDestination(Destination destination) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61617");
        ActiveMQConnection connection = null;
        Session session = null;
        try {
            connection = (ActiveMQConnection) factory.createConnection();

            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


            MessageConsumer consumer = session.createConsumer(destination);
            final AtomicInteger count = new AtomicInteger();
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println(count.incrementAndGet() + "=> receive from " + destination.toString() + ":" + message);
                }
            };

            consumer.setMessageListener(listener);
            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ <= 100) {
                TextMessage message = session.createTextMessage(index + "message");
                producer.send(message);
            }

            Thread.sleep(20000);
        } catch (JMSException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }

        }




    }
}
