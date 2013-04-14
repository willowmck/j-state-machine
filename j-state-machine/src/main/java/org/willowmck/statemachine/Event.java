package org.willowmck.statemachine;

/**
 * Extend the Event for your implementation.
 * @author Will McKinley
 */
public class Event 
{
    protected static final Event ANY_EVENT = new Event( -1, "ANY_EVENT" );
    private int ordinalValue;
    private String name;
    
    protected Event( int ordinalValue, String name )
    {
        if ( null == name )
        {
            throw new NullPointerException( "Event name cannot be null." );
        }
        this.ordinalValue = ordinalValue;
        this.name = name;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 89 * hash + this.ordinalValue;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        final Event other = (Event) obj;
        if (this.ordinalValue != other.ordinalValue) 
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) 
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
