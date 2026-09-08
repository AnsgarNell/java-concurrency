package com.nellpy.workshops.concurrency.structured.police;

import java.util.List;


public record Suspect(String name, String socialSecurityNumber, List<String> vehicles, int arrests) {

}
