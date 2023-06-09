package lab8.functions;

import lab8.patients.Patient;
import java.util.stream.*;
import java.util.*;

public class Functions {
    public void addPatient(List<Patient> list, String fullName, String address, String phone, int medicalCardNumber, String diagnosis) {
        Patient patient = new Patient(fullName, address, phone, medicalCardNumber, diagnosis);
        list.add(patient);
    }
    public void removePatient(int patientToDelete, List<Patient> list) {
        boolean remove = list.removeIf(patient -> patient.getId() == patientToDelete);
        if (!remove) {}
    }
    public void showAllPatients(List<Patient> list) {
        list.forEach(System.out::println);
    }
    public void showAllDiagnosisPatients(List<Patient> list) {
        List<String> diagnoses = list.stream()
                .map(Patient::getDiagnosis)
                .distinct().collect(Collectors.toList());
        System.out.println(diagnoses);
    }
    public List<Patient> searchAndSortMedicalCardNumber(String diagnosis, List<Patient> list) {
        List<Patient> tempList = list.stream()
                .filter(p -> p.getDiagnosis().equals(diagnosis))
                .sorted(Comparator.comparingInt(Patient::getMedicalCardNumber))
                .collect(Collectors.toList());
        Collections.sort(list, Comparator.comparing(Patient::getMedicalCardNumber));
        return tempList;
    }
    public List<Patient> searchFullNameAndPhone(int startCardNumber, List<Patient> list, int endCardNumber) {

        List<Patient> tempList = list.stream()
                .filter(p -> p.getMedicalCardNumber() >= startCardNumber && p.getMedicalCardNumber() <= endCardNumber)
                .collect(Collectors.toList());
        return tempList;
    }
    public List<Patient> foundPatients(String phonePrefix, List<lab8.patients.Patient> list) {
        List<Patient> tempList = list.stream()
                .filter(patient -> patient.getPhone().startsWith(phonePrefix))
                .toList();
        return tempList;
    }
    public List<Patient> ListOfPatientDiagnoses(List<Patient> list){
        Map<String, Integer> diagnosisCount = new HashMap<>();
        for (Patient patient : list) {
            String diagnosis = patient.getDiagnosis();
            diagnosisCount.put(diagnosis, diagnosisCount.getOrDefault(diagnosis, 0) + 1);
        }
        diagnosisCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s - %d пацієнтів\n", entry.getKey(), entry.getValue()));
        return null;
    }
    public static List<Patient> fullNameSort(List<Patient> list){
        Map<String, Long> countByDiagnosis = list.stream()
                .collect(Collectors.groupingBy(Patient::getDiagnosis, Collectors.counting()));
        System.out.println(countByDiagnosis);
        return list;
    }
}