package com.example.demo.mapper;

import com.example.demo._2a.tables.records.CashierRecord;
import com.example.demo.dto.request.cashier.AddCashierRequest;
import com.example.demo.dto.request.cashier.UpdateAllFieldsCashierRequest;
import com.example.demo.dto.request.cashier.UpdateCashierRequest;
import com.example.demo.dto.response.GetCashierResponse;
import com.example.demo.model.Status;
import org.jooq.Record;
import org.jooq.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo._2a.tables.Agents.AGENTS;
import static com.example.demo._2a.tables.Cashier.CASHIER;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CashierMapper {


    CashierMapper INSTANCE = Mappers.getMapper(CashierMapper.class);

//    @Mapping(target = "status", source = "status", qualifiedByName = "statusToInt")
//    Cashier toAddCashierRequest(AddCashierRequest addCashierRequest);

    default CashierRecord toAddCashierRequest(AddCashierRequest addCashierRequest) {
        CashierRecord cashierRecord = new CashierRecord();
        cashierRecord.setName(addCashierRequest.getName());
        cashierRecord.setCashierCode(addCashierRequest.getCashierCode());
        cashierRecord.setAddress(addCashierRequest.getCashierCode());
        cashierRecord.setCity(addCashierRequest.getCity());
        cashierRecord.setAgentId(addCashierRequest.getAgentId());
        cashierRecord.setCurrentBalance(addCashierRequest.getCurrentBalance());
        cashierRecord.setDebtCredit(addCashierRequest.getDebtCredit());
        cashierRecord.setExtradebtbalance(addCashierRequest.getDebtCredit());
        cashierRecord.setMobile(addCashierRequest.getMobile());
        cashierRecord.setStatus(statusToInt(addCashierRequest.getStatus()));
        cashierRecord.setExtraDebtCredit(addCashierRequest.getExtraDebtCredit());
        cashierRecord.setNextPermanentBalance(addCashierRequest.getNextPermanentBalance());
        return cashierRecord;
    }

    //@Mapping(target = "status", source = "status", qualifiedByName = "statusToInt")
    //Cashier toUpdateCashierRequest(CashierRequest cashierRequest);

    default CashierRecord toUpdateCashierRequest(UpdateAllFieldsCashierRequest updateCashierRequest) {
        CashierRecord cashierRecord = new CashierRecord();
        cashierRecord.setName(updateCashierRequest.getName());
        cashierRecord.setCashierCode(updateCashierRequest.getCashierCode());
        cashierRecord.setAddress(updateCashierRequest.getCashierCode());
        cashierRecord.setCity(updateCashierRequest.getCity());
        cashierRecord.setAgentId(updateCashierRequest.getAgentId());
        cashierRecord.setCurrentBalance(updateCashierRequest.getCurrentBalance());
        cashierRecord.setDebtCredit(updateCashierRequest.getDebtCredit());
        cashierRecord.setExtradebtbalance(updateCashierRequest.getDebtCredit());
        cashierRecord.setMobile(updateCashierRequest.getMobile());
        cashierRecord.setStatus(statusToInt(updateCashierRequest.getStatus()));
        cashierRecord.setExtraDebtCredit(updateCashierRequest.getExtraDebtCredit());
        cashierRecord.setNextPermanentBalance(updateCashierRequest.getNextpermanentBalance());
        return cashierRecord;
    }

//    default GetCashierResponse toGetCashierResponse(Result<Record> result){
//        GetCashierResponse getCashierResponse = new GetCashierResponse();
//        getCashierResponse.setCashierCode(result.getValues());
//    }
    //GetCashierResponse toGetCashierResponse(Result<Record> result);

    @Named("statusToInt")
    default Integer statusToInt(Status status) {
        return status.getId();
    }

    default List<GetCashierResponse> cashierMapper(Result<Record> result) {
        return result.stream()
                .map(record -> {
                    GetCashierResponse response = new GetCashierResponse();
                    response.setCashierCode(record.get(CASHIER.CASHIER_CODE));
                    response.setName(record.get(CASHIER.NAME));
                    response.setType(record.get(CASHIER.TYPE));
                    response.setPermanentBalance(record.get(CASHIER.NEXT_PERMANENT_BALANCE));
                    response.setCurrentBalance(record.get(CASHIER.CURRENT_BALANCE));
                    response.setDebtCredit(record.get(CASHIER.DEBT_CREDIT));
                    response.setExtraDebtCredit(record.get(CASHIER.EXTRA_DEBT_CREDIT));
                    response.setCity(record.get(CASHIER.CITY));
                    response.setAddress(record.get(CASHIER.ADDRESS));
                    response.setMobile(record.get(CASHIER.MOBILE));
                    response.setStatus(record.get(CASHIER.STATUS));
                    response.setFullName(record.get(AGENTS.FULL_NAME));
                    return response;
                })
                .collect(Collectors.toList());
    }


    default List<GetCashierResponse> cashierMapper2(List<CashierRecord> result) {
        return result.stream()
                .map(record -> {
                    GetCashierResponse response = new GetCashierResponse();
                    response.setCashierCode(record.getCashierCode());
                    response.setName(record.getName());
                    response.setType(record.getType());
                    response.setPermanentBalance(record.getNextPermanentBalance());
                    response.setCurrentBalance(record.getCurrentBalance());
                    response.setDebtCredit(record.getDebtCredit());
                    response.setExtraDebtCredit(record.getExtraDebtCredit());
                    response.setCity(record.getCity());
                    response.setAddress(record.getAddress());
                    response.setMobile(record.getMobile());
                    response.setStatus(record.getStatus());
                    response.setFullName(AGENTS.FULL_NAME.getName());
                    return response;
                })
                .collect(Collectors.toList());
    }
}

