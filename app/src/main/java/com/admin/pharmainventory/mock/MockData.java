package com.admin.pharmainventory.mock;

import com.admin.pharmainventory.models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<Medicine> getMedicines() {

        List<Medicine> medicineList = new ArrayList<>();

        medicineList.add(new Medicine(
                "Paracetamol 650mg",
                "Dolo 650",
                35,
                10,
                22, 50,
                "2027-12-01",
                "Used to reduce fever and relieve mild to moderate pain such as headache, body ache, and cold.",
                "Analgesic",
                "Micro Labs",
                "BATCH001",
                false));

        medicineList.add(new Medicine(
                "Amoxicillin + Clavulanate 625mg",
                "Mox",
                120,
                15,
                75, 120,
                "2026-08-01",
                "Broad-spectrum antibiotic used for respiratory, urinary, and skin infections.",
                "Antibiotic",
                "Ranbaxy",
                "BATCH002",
                true));

        medicineList.add(new Medicine(
                "Azithromycin 500mg",
                "Azee",
                180,
                20,
                110, 180,
                "2026-11-01",
                "Used for bacterial infections including throat, respiratory, and ear infections.",
                "Antibiotic",
                "Cipla",
                "BATCH003",
                true));

        medicineList.add(new Medicine(
                "Ibuprofen 400mg",
                "Brufen",
                90,
                10,
                55, 90,
                "2027-05-01",
                "NSAID used for pain relief, inflammation, and fever.",
                "NSAID",
                "Abbott",
                "BATCH004",
                false));

        medicineList.add(new Medicine(
                "Metformin 500mg",
                "Glycomet",
                150,
                20,
                95, 150,
                "2028-09-01",
                "Controls blood sugar levels in type 2 diabetes.",
                "Antidiabetic",
                "USV",
                "BATCH005",
                true));

        medicineList.add(new Medicine(
                "Atorvastatin 10mg",
                "Atorlip",
                220,
                25,
                140, 220,
                "2027-03-01",
                "Lowers cholesterol and reduces risk of heart disease.",
                "Cardiac",
                "Cipla",
                "BATCH006",
                true));

        medicineList.add(new Medicine(
                "Pantoprazole 40mg",
                "Pantocid",
                130,
                15,
                80, 130,
                "2026-07-01",
                "Reduces stomach acid, used for acidity and GERD.",
                "Gastro",
                "Sun Pharma",
                "BATCH007",
                false));

        medicineList.add(new Medicine(
                "Cetirizine 10mg",
                "Okacet",
                60,
                10,
                35, 60,
                "2027-01-01",
                "Relieves allergy symptoms like sneezing and itching.",
                "Antihistamine",
                "Cipla",
                "BATCH008",
                false));

        medicineList.add(new Medicine(
                "Paracetamol 650mg",
                "Calpol 650",
                40,
                10,
                25, 45,
                "2026-10-01",
                "Common medicine for fever and pain relief.",
                "Analgesic",
                "GSK",
                "BATCH009",
                false));

        medicineList.add(new Medicine(
                "Levocetirizine 5mg",
                "Levocet",
                75,
                10,
                45, 75,
                "2027-06-01",
                "Advanced antihistamine for allergies.",
                "Antihistamine",
                "Sun Pharma",
                "BATCH010",
                false));

        medicineList.add(new Medicine(
                "Montelukast 10mg",
                "Montek",
                10,
                20,
                85, 140,
                "2027-12-01",
                "Used for asthma prevention and allergic rhinitis.",
                "Respiratory",
                "Sun Pharma",
                "BATCH011",
                true));

        return medicineList;
    }
}