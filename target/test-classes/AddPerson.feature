Feature: Add a person into db

  Scenario Outline: Person is going to be added to database mongo db
    Given i can create new person
    And i sending person with values "<firstname>" and "<surname>" not null, the <age> checks
    Then I should to se new person

    Examples:
      | surname | firstname | age |
      | mark    | gotti     | 12  |
      | mark    | gotti     | 160 |
      | null    | gotti     | 130 |
      | null    | null      | 130 |
      | lia     | null      | 130 |
#
#  private String id;
#  private String firstname;
#  public String surname;
#  public Date birth;
#  private Integer age;
#  private AddressDTO addressDTO;