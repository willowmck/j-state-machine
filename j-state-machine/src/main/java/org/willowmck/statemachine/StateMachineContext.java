package org.willowmck.statemachine;

/**
 *  Your application should implement the StateMachineContext.
 * @author Will McKinley
 */
public interface StateMachineContext 
{
    /**
     * Your application will send new events through the context to pass to the 
     * state machine.  Events, then, need to be publicly defined.
     * This also allows the application to decide what the thread model should be.
     * The StateMachine assumes nothing about the thread model.
     * @param event 
     */
    public void addEvent( Event event );
    
    /**
     * The StateMachine leaves it up to the context to handle logging.
     * @param transition 
     */
    public void logTransition( StateMachineTransition transition ); 
    
    /**
     * The context is also in control of the current state.  This will be called 
     * by the StateMachine when it receives an event.
     * @return current state
     */
    public State getCurrentState();
    
    /**
     * When the context has done it's job via runEvent, this method will be called
     * by the StateMachine.  This happens automatically when runEvent finishes.
     * Thus, if you are using a different thread to execute the handler from runEvent, 
     * then you may want to cache the current state when setState is called until
     * your handler job is complete.
     * @param state next state
     */
    public void setState( State state );
}
