
/**
 * The Denomination class represents the number of different coin denominations.
 * It keeps track of the number of dollars, quarters, dimes, nickels, and
 * pennies.
 */
public class Denomination {
    private int nDollars = 0;
    private int nQuarters = 0;
    private int nDimes = 0;
    private int nNickels = 0;
    private int nPennies = 0;

    /**
     * Adds the specified number of dollars to the denomination.
     *
     * @param nAmount The number of dollars to add.
     */
    public void addDollars(int nAmount) {
        this.nDollars += (1 * nAmount);
    }

    /**
     * Adds the specified number of quarters to the denomination.
     *
     * @param nAmount The number of quarters to add.
     */
    public void addQuarters(int nAmount) {
        this.nQuarters += (1 * nAmount);
    }

    /**
     * Adds the specified number of dimes to the denomination.
     *
     * @param nAmount The number of dimes to add.
     */
    public void addDimes(int nAmount) {
        this.nDimes += (1 * nAmount);
    }

    /**
     * Adds the specified number of nickels to the denomination.
     *
     * @param nAmount The number of nickels to add.
     */
    public void addNickels(int nAmount) {
        this.nPennies += (1 * nAmount);
    }

    /**
     * Adds the specified number of pennies to the denomination.
     *
     * @param nAmount The number of pennies to add.
     */
    public void addPennies(int nAmount) {
        this.nPennies += (1 * nAmount);
    }

    /**
     * Retrieves the number of dollars in the denomination.
     *
     * @return The number of dollars.
     */
    public int getDollars() {
        return nDollars;
    }

    /**
     * Retrieves the number of quarters in the denomination.
     *
     * @return The number of quarters.
     */
    public int getQuarters() {
        return nQuarters;
    }

    /**
     * Retrieves the number of dimes in the denomination.
     *
     * @return The number of dimes.
     */
    public int getDimes() {
        return nDimes;
    }

    /**
     * Retrieves the number of nickels in the denomination.
     *
     * @return The number of nickels.
     */
    public int getNickels() {
        return nNickels;
    }

    /**
     * Retrieves the number of pennies in the denomination.
     *
     * @return The number of pennies.
     */
    public int getPennies() {
        return nPennies;
    }

    /**
     * Removes the specified number of dollars from the denomination.
     *
     * @param nAmount The number of dollars to remove.
     */
    public void removeDollars(int nAmount) {
        this.nDollars -= (1 * nAmount);
    }

    /**
     * Removes the specified number of quarters from the denomination.
     *
     * @param nAmount The number of quarters to remove.
     */
    public void removeQuarters(int nAmount) {
        this.nQuarters -= nAmount;
    }

    /**
     * Removes the specified number of dimes from the denomination.
     *
     * @param nAmount The number of dimes to remove.
     */
    public void removeDimes(int nAmount) {
        this.nDimes -= nAmount;
    }

    /**
     * Removes the specified number of nickels from the denomination.
     *
     * @param nAmount The number of nickels to remove.
     */
    public void removeNickels(int nAmount) {
        this.nNickels -= nAmount;
    }

    /**
     * Removes the specified number of pennies from the denomination.
     *
     * @param nAmount The number of pennies to remove.
     */
    public void removePennies(int nAmount) {
        this.nPennies -= (1 * nAmount);
    }
}
