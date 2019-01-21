public class Monster extends Character
{
    public Monster(String name, int newAC, int newMaxHP, int newInitMod)
    {
        super(name, newAC, newMaxHP, newInitMod, type.MONSTER);
    }
}
