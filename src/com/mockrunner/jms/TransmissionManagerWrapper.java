package com.mockrunner.jms;

import java.util.ArrayList;
import java.util.List;

import com.mockrunner.mock.jms.MockMessageConsumer;
import com.mockrunner.mock.jms.MockMessageProducer;

/**
 * A wrapper around {@link QueueTransmissionManager} and
 * {@link TopicTransmissionManager} and {@link GenericTransmissionManager}. 
 * Can be used to access all senders, publishers, receivers and subscribers
 * transparently as producers and consumers.
 */
public class TransmissionManagerWrapper
{ 
    private QueueTransmissionManager queueManager;
    private TopicTransmissionManager topicManager;
    private GenericTransmissionManager genericManager;
    
    public TransmissionManagerWrapper(QueueTransmissionManager queueManager, TopicTransmissionManager topicManager, GenericTransmissionManager genericManager)
    {
        this.queueManager = queueManager;
        this.topicManager = topicManager;
        this.genericManager = genericManager;
    }
    
    /**
     * Returns the underlying {@link QueueTransmissionManager}.
     * @return the {@link QueueTransmissionManager}
     */
    public QueueTransmissionManager getQueueTransmissionManager()
    {
        return queueManager;
    }
    
    /**
     * Returns the underlying {@link TopicTransmissionManager}.
     * @return the {@link TopicTransmissionManager}
     */
    public TopicTransmissionManager getTopicTransmissionManager()
    {
        return topicManager;
    }
    
    /**
     * Returns the underlying {@link GenericTransmissionManager}.
     * @return the {@link GenericTransmissionManager}
     */
    public GenericTransmissionManager getGenericTransmissionManager()
    {
        return genericManager;
    }
    
    /**
     * Returns the {@link com.mockrunner.mock.jms.MockMessageProducer} objects
     * with the specified index resp. <code>null</code>, if no such
     * {@link com.mockrunner.mock.jms.MockMessageProducer} exists.
     * @param index the index
     * @return the {@link com.mockrunner.mock.jms.MockMessageProducer} object
     */
    public MockMessageProducer getMessageProducer(int index)
    {
        if(getMessageProducerList().size() <= index || index < 0) return null;
        return (MockMessageProducer)getMessageProducerList().get(index);
    }

    /**
     * Returns a list of all senders and publishers
     * as {@link com.mockrunner.mock.jms.MockMessageProducer}
     * objects.
     * @return the list of {@link com.mockrunner.mock.jms.MockMessageProducer} objects
     */
    public List getMessageProducerList()
    {
        List resultList = new ArrayList();
        resultList.addAll(queueManager.getQueueSenderList());
        resultList.addAll(topicManager.getTopicPublisherList());
        resultList.addAll(genericManager.getMessageProducerList());
        return resultList;
    }
    
    /**
     * Returns the {@link com.mockrunner.mock.jms.MockMessageConsumer} objects
     * with the specified index resp. <code>null</code>, if no such
     * {@link com.mockrunner.mock.jms.MockMessageConsumer} exists.
     * @param index the index
     * @return the {@link com.mockrunner.mock.jms.MockMessageConsumer} object
     */
    public MockMessageConsumer getMessageConsumer(int index)
    {
        if(getMessageConsumerList().size() <= index || index < 0) return null;
        return (MockMessageConsumer)getMessageConsumerList().get(index);
    }

    /**
     * Returns a list of all receivers and subcribers
     * as {@link com.mockrunner.mock.jms.MockMessageConsumer}
     * objects. Includes durable subscribers.
     * @return the list of {@link com.mockrunner.mock.jms.MockMessageConsumer} objects
     */
    public List getMessageConsumerList()
    {
        List resultList = new ArrayList();
        resultList.addAll(queueManager.getQueueReceiverList());
        resultList.addAll(topicManager.getTopicSubscriberList());
        resultList.addAll(topicManager.getDurableTopicSubscriberMap().values());
        return resultList;
    }
}
