package com.admin.pharmainventory.mock;

import com.admin.pharmainventory.models.Medicine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<Medicine> getMedicines() {

        List<Medicine> medicineList = new ArrayList<>();

        medicineList.add(new Medicine(
                "P001",
                "Paracetamol 650mg",
                "Dolo 650",
                35,
                10,
                22, 50,
                LocalDate.of(2027, 12, 1),
                "Used to reduce fever and relieve mild to moderate pain such as headache, body ache, and cold.",
                "Analgesic",
                "Micro Labs",
                "BATCH001",
                false));

        medicineList.add(new Medicine(
                "P002",
                "Amoxicillin + Clavulanate 625mg",
                "Mox",
                120,
                15,
                75, 120,
                LocalDate.of(2026, 8, 1),
                "Broad-spectrum antibiotic used for respiratory, urinary, and skin infections.",
                "Antibiotic",
                "Ranbaxy",
                "BATCH002",
                true));

        medicineList.add(new Medicine(
                "P003",
                "Azithromycin 500mg",
                "Azee",
                180,
                20,
                110, 180,
                LocalDate.of(2026, 11, 1),
                "Used for bacterial infections including throat, respiratory, and ear infections.",
                "Antibiotic",
                "Cipla",
                "BATCH003",
                true));

        medicineList.add(new Medicine(
                "P004",
                "Ibuprofen 400mg",
                "Brufen",
                90,
                10,
                55, 90,
                LocalDate.of(2027, 5, 1),
                "NSAID used for pain relief, inflammation, and fever.",
                "NSAID",
                "Abbott",
                "BATCH004",
                false));

        medicineList.add(new Medicine(
                "P005",
                "Metformin 500mg",
                "Glycomet",
                150,
                20,
                95, 150,
                LocalDate.of(2028, 9, 1),
                "Controls blood sugar levels in type 2 diabetes.",
                "Antidiabetic",
                "USV",
                "BATCH005",
                true));

        medicineList.add(new Medicine(
                "P006",
                "Atorvastatin 10mg",
                "Atorlip",
                220,
                25,
                140, 220,
                LocalDate.of(2027, 3, 1),
                "Lowers cholesterol and reduces risk of heart disease.",
                "Cardiac",
                "Cipla",
                "BATCH006",
                true));

        medicineList.add(new Medicine(
                "P007",
                "Pantoprazole 40mg",
                "Pantocid",
                130,
                15,
                80, 130,
                LocalDate.of(2026, 7, 1),
                "Reduces stomach acid, used for acidity and GERD.",
                "Gastro",
                "Sun Pharma",
                "BATCH007",
                false));

        medicineList.add(new Medicine(
                "P008",
                "Cetirizine 10mg",
                "Okacet",
                60,
                10,
                35, 60,
                LocalDate.of(2027, 1, 1),
                "Relieves allergy symptoms like sneezing and itching.",
                "Antihistamine",
                "Cipla",
                "BATCH008",
                false));

        medicineList.add(new Medicine(
                "P009",
                "Paracetamol 650mg",
                "Calpol 650",
                40,
                10,
                25, 45,
                LocalDate.of(2026, 10, 1),
                "Common medicine for fever and pain relief.",
                "Analgesic",
                "GSK",
                "BATCH009",
                false));

        medicineList.add(new Medicine(
                "P010",
                "Levocetirizine 5mg",
                "Levocet",
                75,
                10,
                45, 75,
                LocalDate.of(2027, 6, 1),
                "Advanced antihistamine for allergies.",
                "Antihistamine",
                "Sun Pharma",
                "BATCH010",
                false));

        medicineList.add(new Medicine(
                "P011",
                "Montelukast 10mg",
                "Montek",
                10,
                20,
                85, 140,
                LocalDate.of(2027, 12, 1),
                "Used for asthma prevention and allergic rhinitis.",
                "Respiratory",
                "Sun Pharma",
                "BATCH011",
                true));

        return medicineList;
    }
}