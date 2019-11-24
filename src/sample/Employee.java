package sample;

import java.util.regex.*;

/**
 * @author Daniel Miller
 *     <p>The Employee class contains the content necessary to create an Employee object with a
 *     corresponding username and password, with which that employee will be able to use to securely
 *     log in and start/view production. Regex patterns are used to ensure that the username and
 *     password fields are in line with typical security standards.
 */
public class Employee {
  String name;
  String username;
  String password;
  String email;
  private static final Pattern[] inputRegexes = new Pattern[3];

  static {
    inputRegexes[0] = Pattern.compile(".*[A-Z].*");
    inputRegexes[1] = Pattern.compile(".*[a-z].*");
    inputRegexes[2] = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");
  }

  Employee(String name, String password) {
    checkName(name);
    isValidPassword(password);
  }

  public String toString() {
    return "Employee Details\nName : "
        + name
        + "\nUsername : "
        + username
        + "\nEmail : "
        + email
        + "@oracleacademy.Test\nInitial Password : "
        + password;
  }

  private void setName(String na) {
    name = na;
  }

  private void setUsername(String user) {
    username = user;
  }

  private void setPassword(String pass) {
    password = pass;
  }

  private void setEmail(String mail) {
    email = mail;
  }

  private void createUsername(String name) {

  }

  private void isValidPassword(String pass) {
    boolean inputMatches = true;
    for (Pattern inputRegex : inputRegexes) {
      if (!inputRegex.matcher(pass).matches()) {
        inputMatches = false;
      }
    }
    if (inputMatches) {
      setPassword(pass);
    } else {
      setPassword("pw");
    }
  }

  private void checkName(String name) {
    setName(name);
    int spaceIndex = name.indexOf(' ');
    if (spaceIndex == -1) {
      setUsername("default");
      setEmail("user");
    } else {
      String name1 = name.substring(0, spaceIndex);
      String name2 = name.substring(spaceIndex + 1, name.length());
      name1 = name1.toLowerCase();
      name2 = name2.toLowerCase();
      setEmail(name1 + "." + name2);
      name1 = name1.substring(0, 1);
      setUsername(name1 + name2);
    }
  }
}
