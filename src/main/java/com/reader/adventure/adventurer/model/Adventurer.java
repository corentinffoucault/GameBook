package com.reader.adventure.adventurer.model;

public class Adventurer {
    private String name;

    private Integer agility;
    private Integer strength;
    private Integer intelligence;
    private Integer charisma;
    private Integer courage;

    private Integer attack;
    private Integer parry;
    private Integer throwing;
    private Integer dodge;
    private Integer search;

    private Integer inventiveness;
    private Integer physical_magic;
    private Integer psychic_magic;
    private Integer magic_resistance;

    private Integer gold;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAgility() { return agility; }
    public void setAgility(Integer agility) { this.agility = agility; }

    public Integer getGold() { return gold; }
    public void setGold(Integer gold) { this.gold = gold; }

    public Integer getIntelligence() { return intelligence; }
    public void setIntelligence(Integer intelligence) { this.intelligence = intelligence; }

    public Integer getStrength() { return strength; }
    public void setStrength(Integer strength) { this.strength = strength; }

    public Integer getCharisma() { return charisma; }
    public void setCharisma(Integer charisma) { this.charisma = charisma; }

    public Integer getCourage() { return courage; }
    public void setCourage(Integer courage) { this.courage = courage; }

    public Integer getAttack() { return attack;  }
    public void setAttack(Integer attack) { this.attack = attack;  }

    public Integer getParry() { return parry;  }
    public void setParry(Integer parry) { this.parry = parry;  }

    public Integer getThrowing() { return throwing;  }
    public void setThrowing(Integer throwing) { this.throwing = throwing;  }

    public Integer getDodge() { return dodge;  }
    public void setDodge(Integer dodge) { this.dodge = dodge;  }

    public Integer getSearch() { return search;  }
    public void setSearch(Integer search) { this.search = search;  }

    public Integer getInventiveness() { return inventiveness;  }
    public void setInventiveness(Integer inventiveness) { this.inventiveness = inventiveness;  }

    public Integer getPhysical_magic() { return physical_magic;  }
    public void setPhysical_magic(Integer physical_magic) { this.physical_magic = physical_magic;  }

    public Integer getPsychic_magic() { return psychic_magic;  }
    public void setPsychic_magic(Integer psychic_magic) { this.psychic_magic = psychic_magic;  }

    public Integer getMagic_resistance() { return magic_resistance;  }
    public void setMagic_resistance(Integer magic_resistance) { this.magic_resistance = magic_resistance;  }

}
