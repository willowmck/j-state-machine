package org.willowmck.statemachine;

/**
 * One StateMachineEntry consists of a start state, event, handler, and next state.
 * @author Will McKinley
 */
public class StateMachineTransition 
{
    private State startState;
    private Event event;
    private Handler handler;
    private State endState;
    
    public StateMachineTransition( State startState,
           Event event,
           Handler handler,
           State endState )
    {
        this.startState = startState;
        this.event = event;
        this.handler = handler;
        this.endState = endState;
    }

    public State getStartState() 
    {
        return startState;
    }

    public Event getEvent() 
    {
        return event;
    }

    public Handler getHandler() 
    {
        return handler;
    }

    public State getEndState() 
    {
        return endState;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 31 * hash + (this.startState != null ? this.startState.hashCode() : 0);
        hash = 31 * hash + (this.event != null ? this.event.hashCode() : 0);
        hash = 31 * hash + (this.handler != null ? this.handler.hashCode() : 0);
        hash = 31 * hash + (this.endState != null ? this.endState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final StateMachineTransition other = (StateMachineTransition) obj;
        if (this.startState != other.startState) 
        {
            return false;
        }
        if (this.event != other.event) 
        {
            return false;
        }
        if (this.handler != other.handler && (this.handler == null || 
                !this.handler.equals(other.handler))) 
        {
            return false;
        }
        if (this.endState != other.endState) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StateMachineTransition{" + "startState=" + startState + ", event=" + event + ", handler=" + handler.getClass().getCanonicalName() + ", endState=" + endState + '}';
    }
    
    
}
