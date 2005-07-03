package com.mockrunner.mock.connector.cci;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.Interaction;
import javax.resource.cci.LocalTransaction;
import javax.resource.cci.ResultSetInfo;

import com.mockrunner.connector.InteractionHandler;

/**
 * Mock implementation of <code>Connection</code>.
 */
public class MockConnection implements Connection 
{
	private boolean closed;
	private InteractionHandler interactionHandler;
    private ConnectionMetaData metaData;
    private LocalTransaction localTransaction;
	
	public MockConnection() 
    {
		closed = false;
        metaData = new MockConnectionMetaData();
        localTransaction = new MockLocalTransaction();
	}
    
    public MockLocalTransaction getMockLocalTransaction() 
    {
        if(localTransaction instanceof MockLocalTransaction)
        {
            return (MockLocalTransaction)localTransaction;
        }
        return null;
    }

	public void close() throws ResourceException 
    {
		closed = true;
	}

	public Interaction createInteraction() throws ResourceException 
    {
		return new MockInteraction(this);
	}

	public LocalTransaction getLocalTransaction() throws ResourceException 
    {
		return localTransaction;
	}

	public ConnectionMetaData getMetaData() throws ResourceException 
    {
		return metaData;
	}

	public ResultSetInfo getResultSetInfo() throws ResourceException 
    {
		return null;
	}
	
	public boolean isClosed()
	{
		return closed;
	}

	public InteractionHandler getInteractionHandler() 
    {
		return interactionHandler;
	}
    
    /**
     * Sets this connections <code>InteractionHandler</code>
     * @param interactionHandler the <code>InteractionHandler</code>
     */
    public void setInteractionHandler(InteractionHandler interactionHandler) 
    {
        this.interactionHandler = interactionHandler;
    }
    
    /**
     * Sets this connections meta data
     * @param metaData the meta data
     */
    public void setMetaData(ConnectionMetaData metaData)
    {
        this.metaData = metaData;
    }
    
    /**
     * Sets the <code>LocalTransaction</code>
     * @param localTransaction the <code>LocalTransaction</code>
     */
    public void setLocalTransaction(LocalTransaction localTransaction)
    {
        this.localTransaction = localTransaction;
    }
}
