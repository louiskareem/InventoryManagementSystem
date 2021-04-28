package model;

public enum Role
{
    MANAGER(1),
    EMPLOYEE(2);

    private final int code;

    /**
     * Set role as int
     * @param code
     */
    Role(int code)
    {
        this.code = code;
    }

    /**
     * Return role as integer
     * @return
     */
    public int toInt()
    {
        return code;
    }
}
