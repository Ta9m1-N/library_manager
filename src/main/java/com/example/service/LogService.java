package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Log;
import com.example.repository.LogRepository;

@Service
public class LogService {
	private final LogRepository logRepository;
	
	@Autowired
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	public List<Log> findAll(){
		return this.logRepository.findAll();
	}
	
	public Log findById(Integer id) {
		Optional<Log> optionalLog = this.logRepository.findById(id);
		Log log = optionalLog.get();
		return log;
	}
	
	public List<Log> findByUserId(Integer userId) {
		List<Log> logs = this.logRepository.findByUserId(userId);
		return logs;
	}
	
	public Log insert(Integer libraryId,Integer userId,LocalDateTime rentDate,LocalDateTime returnDueDate){
		Log log = new Log();
		log.setLibraryId(libraryId);
		log.setUserId(userId);
		log.setRentDate(rentDate);
		log.setReturnDueDate(returnDueDate);
		log.setReturnDate(null);
		return this.logRepository.save(log);
	}
	
	public Log updateReturnDate(Integer libraryId,Integer userId,LocalDateTime returnDate) {
		Log log = this.logRepository.findFirstByLibraryIdAndUserIdOrderByRentDateDesc(libraryId,userId);
		log.setReturnDate(returnDate);
		return this.logRepository.save(log);
	}
}
