package org.willowmck.statemachine;

/**
 * Override the Handler to handle Events for your State transitions.
 * @author will
 */
public interface Handler 
{
    /**
     * The Handler gets passed the event to run.  Should it need any application
     * data, this should be provided by the context.  You will have to cast the
     * context (I know, this isn't ideal).  
     * The other way to handle this if you have uniform data you want to pass is
     * by extending the Event class to contain application data.
     * @param event the event to handle
     * @param context application context
     */
    public void handleEvent( Event event, StateMachineContext context);
}
