package ss7_abstract_interface.thuc_hanh.thuc_hanh_1;

import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.animal.Animal;
import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.animal.Chicken;
import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.animal.Tiger;
import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.edible.Edible;
import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.fruit.Apple;
import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.fruit.Fruit;
import ss7_abstract_interface.thuc_hanh.thuc_hanh_1.fruit.Orange;

public class AbstractAndInterfaceTests {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        animals[0] = new Tiger();
        animals[1] = new Chicken();
        for (Animal animal : animals) {
            System.out.println(animal.makeSound());

            if (animal instanceof Chicken) {
                Edible edibler = (Chicken) animal;
                System.out.println(edibler.howToEat());
            }
        }

        Fruit[] fruits = new Fruit[2];
        fruits[0] = new Orange();
        fruits[1] = new Apple();
        for (Fruit fruit : fruits) {
            System.out.println(fruit.howToEat());
        }

    }
}