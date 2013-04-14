package org.willowmck.statemachine;

import java.util.HashSet;
import java.util.logging.Logger;

/**
 * The StateMachine class
 * 
 * @author Will McKinley
 */
public class StateMachine 
{
    private HashSet<StateMachineTransition> transitions = new HashSet<StateMachineTransition>();
    private int stateCreationCounter = 1;
    private int eventCreationCounter = 1;
    private boolean initialized = false;
    private final Object initLock = new Object();
    private StateMachineContext context;
    private boolean done = false;
    
    /**
     * Using an empty constructor allows the context to use Spring.
     */
    public void StateMachine()
    {
    }
    
    public State createState( String name ) 
            throws StateMachineInitializedException
    {
        checkInitialized();
        return new State( stateCreationCounter++, name, false );
    }
    
    public State createState( String name, boolean isTerminal )
            throws StateMachineInitializedException
    {
        return new State( stateCreationCounter++, name, isTerminal );
    }
    
    public Event createEvent( String name )
            throws StateMachineInitializedException
    {
        return new Event( eventCreationCounter++, name );
    }
    
    public void addStateMachineTransition( StateMachineTransition transition )
            throws StateMachineInitializedException
    {
        transitions.add(transition);
    }
    
    /**
     * Makes the StateMachine ready to run.  
     * When this is called, no more transitions may be added
     */
    public void init( StateMachineContext context ) throws StateMachineInitializedException, StateMachineUninitializedException
    {
        if ( initialized )
        {
            throw new StateMachineInitializedException( 
                    "StateMachine is already initialized." );
        }
        if ( done )
        {
            throw new StateMachineInitializedException( 
                    "StateMachine has reached terminal state." );
        }
        synchronized( initLock )
        {
            if (transitions.isEmpty())
            {
                throw new StateMachineUninitializedException(
                        "You should add transitions before initializing the machine." );
            }
            this.context = context;
            initialized = true;
        }
    }
    
    public void runEvent( Event event ) 
            throws StateMachineUninitializedException 
    {
        synchronized( initLock )
        {
            if ( !initialized )
            {
                throw new StateMachineUninitializedException();
            }
            State currentState = context.getCurrentState();
            if ( currentState == null )
            {
                throw new NullPointerException( "The context does not have a current state!" );
            }
            StateMachineTransition transition = findBestTransition( currentState, event );
            context.logTransition(transition);
            Handler handler = transition.getHandler();
            handler.handleEvent(event, context);
            context.setState(transition.getEndState());
            if ( transition.getEndState().isTerminal())
            {
                done = true;
            }
        }
    }

    private void checkInitialized() throws StateMachineInitializedException 
    {
        synchronized( initLock )
        {
            if ( initialized )
            {
                throw new StateMachineInitializedException(
                        "Illegal modification operation.  StateMachine has already been initialized."); 
            }
            if ( done )
            {
                throw new StateMachineInitializedException(
                        "StateMachine has reached terminal state." );
            }
        }
    }

    private StateMachineTransition findBestTransition(State currentState, Event event) 
    {
        StateMachineTransition possibleMatch = null;
        for ( StateMachineTransition transition : transitions )
        {
            if ( transition.getStartState().equals(currentState) 
                && transition.getEvent().equals(event) )
            {
                return transition;
            }
            else if ( transition.getStartState().equals(State.ANY_STATE) && 
                    transition.getEvent().equals( event ) )
            {
                possibleMatch = transition;
            }
            else if ( transition.getStartState().equals(currentState) &&
                    transition.getEvent().equals( Event.ANY_EVENT )   &&
                    null == possibleMatch)
            {
                possibleMatch = transition;
            }
            else if ( transition.getStartState().equals(State.ANY_STATE) &&
                    transition.getEvent().equals( Event.ANY_EVENT)       &&
                    null == possibleMatch)
            {
                possibleMatch = transition;
            }
        }
        return possibleMatch;
    }
}
