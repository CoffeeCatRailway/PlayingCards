package coffeecatteam.playingcards.util;

import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionType;

public class CardProperties {

    public enum Suit {

        SPADES("spades"),
        DIAMONDS("diamonds"),
        CLUBS("clubs"),
        HEARTS("hearts");

        private String name;

        private Suit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Number {

        ACE("ace", 1),
        TWO("two", 2),
        THREE("three", 3),
        FOUR("four", 4),
        FIVE("five", 5),
        SIX("six", 6),
        SEVEN("seven", 7),
        EIGHT("eight", 8),
        NINE("nine", 9),
        TEN("ten", 10),
        JACK("jack", 11),
        QUEEN("queen", 12),
        KING("king", 13);

        private String name;
        private int damage;

        private Number(String name, int damage) {
            this.name = name;
            this.damage = damage;
        }

        public String getName() {
            return name;
        }

        public int getDamage() {
            return damage;
        }
    }

    public enum Magic {

        REGENERATION("regeneration", PotionTypes.REGENERATION),
        LEAPING("leaping", PotionTypes.LEAPING),
        SLOWNESS("slowness", PotionTypes.SLOWNESS),
        HARMING("harming", PotionTypes.HARMING),
        HEALING("healing", PotionTypes.HEALING),
        POISON("poison", PotionTypes.POISON),
        SWIFTNESS("swiftness", PotionTypes.SWIFTNESS);

        private String name;
        private PotionType effect;

        private Magic(String name, PotionType effect) {
            this.name = name;
            this.effect = effect;
        }

        public String getName() {
            return name;
        }

        public PotionType getEffect() {
            return effect;
        }
    }
}
