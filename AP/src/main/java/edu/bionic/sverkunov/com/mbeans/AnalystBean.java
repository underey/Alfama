package edu.bionic.sverkunov.com.mbeans;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport;
import edu.bionic.sverkunov.com.services.AnalystServiceI;

@Named("ab")
@Scope("session")
public class AnalystBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private AnalystServiceI analystService;

	private int option;
	private int category;
	private PeriodTotalReport ptr;
	private Date from;
	private Date until;
	private Date maxDate;

	public AnalystBean() {
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public PeriodTotalReport getPtr() {
		return ptr;
	}

	public void setPtr(PeriodTotalReport ptr) {
		this.ptr = ptr;
	}

	public AnalystServiceI getAnalystService() {
		return analystService;
	}

	public void setAnalystService(AnalystServiceI analystService) {
		this.analystService = analystService;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public Date getMaxDate() {
		return new Date();
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public String updateAnalystScreen(String option) {
		int o = Integer.parseInt(option);
		if (o == 1) {
			java.sql.Timestamp f = new java.sql.Timestamp(from.getTime());
			java.sql.Timestamp u = new java.sql.Timestamp(until.getTime());
			ptr = analystService.getTotalReport(f, u);
		} else if (o == 2) {
			java.sql.Timestamp f = new java.sql.Timestamp(from.getTime());
			java.sql.Timestamp u = new java.sql.Timestamp(until.getTime());
			ptr = analystService.getDailyReport(f, u, category);
		}
		return "analystReportPage";
	}

}
