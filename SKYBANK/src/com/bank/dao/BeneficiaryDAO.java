package com.bank.dao;

import java.util.List;

import com.bank.dto.Beneficiary;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public interface BeneficiaryDAO {

	int addBeneficiary(Beneficiary beneficiary) throws SomethingWentWrongException, NoRecordFoundException;

	int deleteBeneficiary(String beneficiaryAccountNumber, String payeeAccountNumber)
			throws SomethingWentWrongException, NoRecordFoundException;

	List<Beneficiary> viewBeneficiary(String payeeAccountNumber)  throws SomethingWentWrongException, NoRecordFoundException;
}
