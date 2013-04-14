package org.willowmck.statemachine;

import java.util.HashSet;
import java.util.Set;

/**
 * Extend this enum to create your state definitions.
 * @author Will McKinley
 */
public class State 
{
    final static State ANY_STATE = new State( -1, "ANY_STATE", false );
    final static State SAME_STATE = new State( 0, "SAME_STATE", false );
    private int ordinalValue;
    private String name;
    private boolean isTerminal = false;
    
    protected State( int ordinalValue, String name, boolean isTerminal )
    {
        if ( null == name )
        {
            throw new NullPointerException( "State name cannot be null." );
        }
        this.ordinalValue = ordinalValue;
        this.name = name;
        this.isTerminal = isTerminal;
    }
    
    public boolean isTerminal()
    {
        return isTerminal;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 83 * hash + this.ordinalValue;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.isTerminal ? 1 : 0);
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
        final State other = (State) obj;
        if (this.ordinalValue != other.ordinalValue) 
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) 
        {
            return false;
        }
        if (this.isTerminal != other.isTerminal) 
        {
            return false;
        }
        return true;
    }
    
    public String toString()
    {
        return name;
    }
    
}
