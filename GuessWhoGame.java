import java.util.*;

public class GuessWhoGame {
    private final int attributeCount;
    private final int personCount;
    private final String[] names;
    private final String[] attributes;

    public GuessWhoGame(int attributeCount, int personCount, String[] names, String[] attributes) {
        this.attributeCount = attributeCount;
        this.personCount = personCount;
        this.names = names;
        this.attributes = attributes;
    }

    public boolean play(Scanner scanner) {
        List<Person> people = createPeople();
        System.out.println("Welcome to Guess Who! Here are the people and their attributes:");

        // Display people and their attributes
        for (Person person : people) {
            System.out.println(person.getName() + ": " + getPersonAttributes(person));
        }

        // Choose a random person as the subject to be guessed
        Person subject = people.get(new Random().nextInt(personCount));

        System.out.println("Guess who the mystery person is! You have 3 questions to narrow it down.");

        for (int questionNumber = 1; questionNumber <= 3; questionNumber++) {
            System.out.println("Question " + questionNumber + ": What attribute do you want to ask for?");
            // Display the attribute options
            for (int i = 0; i < attributeCount; i++) {
                System.out.println((i + 1) + ". " + attributes[i]);
            }

            // Get the user's choice of attribute
            int attributeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Check if the chosen attribute is valid
            if (attributeChoice < 1 || attributeChoice > attributeCount) {
                System.out.println("Invalid attribute choice, please try again.");
                questionNumber--;
                continue;
            }

            // Display the list of people who have the chosen attribute
            List<Person> peopleWithAttribute = new ArrayList<>();
            for (Person person : people) {
                if (person.hasAttribute(attributeChoice)) {
                    peopleWithAttribute.add(person);
                }
            }

            System.out.print("People that are " + attributes[attributeChoice - 1] + ": ");
            for (Person person : peopleWithAttribute) {
                System.out.print(person.getName() + " ");
            }
            System.out.println();

            // Remove people who do not have the chosen attribute
            people.retainAll(peopleWithAttribute);
            
         // Display remaining people and their attributes
            displayRemainingPeople(people);
        }

        // Guess the mystery person
        System.out.println("Who do you think the mystery person is? Please type the name of the person:");
        String guessName = scanner.nextLine();

        // Check if the guess is correct
        if (subject.getName().equalsIgnoreCase(guessName)) {
            System.out.println("Congratulations, you guessed correctly!");
            return true;
        } else {
            System.out.println("Sorry, the mystery person was " + subject + ".");
            return false;
        }
    }
    private void displayRemainingPeople(List<Person> people) {
        System.out.println("Remaining people:");
        for (Person person : people) {
            System.out.println(person.getName() + ": " + getPersonAttributes(person));
        }
    }

    private List<Person> createPeople() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < personCount; i++) {
            people.add(createPerson(names[i], attributeCount));
        }
        Collections.shuffle(people);
        return people;
    }
    private Person createPerson(String name, int attributeCount) {
        Person person = new PersonA(name, attributeCount);
        List<Integer> availableAttributes = new ArrayList<>();
        for (int i = 0; i < attributeCount; i++) {
            availableAttributes.add(i);
        }
        Collections.shuffle(availableAttributes);
        for (int i = 0; i < Main.MAX_ATTRIBUTES_PER_PERSON; i++) {
            int attribute = availableAttributes.get(i);
            person.setAttribute(attribute + 1, true);
        }
        return person;
    }

    private String getPersonAttributes(Person person) {
        StringBuilder sb = new StringBuilder();
        int attributesPrinted = 0;
        for (int i = 0; i < attributeCount; i++) {
            if (person.hasAttribute(i + 1)) {
                sb.append(attributes[i]);
                attributesPrinted++;
                if (attributesPrinted < Main.MAX_ATTRIBUTES_PER_PERSON) {
                    sb.append(", ");
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }
}