# new feature
# Tags: optional

Feature: A szerelo csovet lyukaszt, ami nem lyukas

  Scenario: A szerelo megprobal egy csovet lyukasztani, sikerrel jar
    Given Egy tábla egy ciszterna ("c1"), hozza csatolva egy csovel ("cs1").
    And Egy szerelo ("sz1"), ami "cs1"-n all.
    When "sz1" szerelo megprobalja kilyukasztani a "cs1" csovet
    Then A "cs1" cso lukas lesz