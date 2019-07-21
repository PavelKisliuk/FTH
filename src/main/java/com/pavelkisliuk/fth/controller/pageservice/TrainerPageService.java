package com.pavelkisliuk.fth.controller.pageservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthClientPersonalData;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthTrainerData;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.ClientGroupByTrainerSpecifier;
import com.pavelkisliuk.fth.specifier.select.TrainerByIdSpecifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainerPageService implements FthService {
	@Override
	public String serve(FthData data) throws FthControllerException {
		Map<String, List<FthData>> responseJson = new HashMap<>();
		FthLong trainerId = (FthLong) data;

		try {
			List<FthData> trainerData = FthRepository.INSTANCE.query(new TrainerByIdSpecifier(trainerId));
			responseJson.put("trainer", trainerData);

			List<FthData> clientGroup = FthRepository.INSTANCE.query(new ClientGroupByTrainerSpecifier(trainerId));
			responseJson.put("client", clientGroup);
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in TrainerPageService -> serve(FthData).", e);
		}
		String s = new Gson().toJson(responseJson);
		return new Gson().toJson(responseJson);
	}
}
/*{
"trainer":
		[{"trainerId":1,"name":"Павел","surname":"Кислюк","photoPath":"https://pp.userapi.com/c637229/v637229874/21fa5/v6r_UTH_uCw.jpg"}],
"client":
		[
			{"clientID":1,"firstName":"Pavel","lastName":"Kisliuk","photoPath":"https://pp.userapi.com/c638330/v638330874/419bb/AzS3PYR_kf0.jpg","trainerId":0},
			{"clientID":1,"firstName":"Роман","lastName":"Жминько","photoPath":"https://pp.userapi.com/c855216/v855216470/94258/dYssd2RWmhQ.jpg","trainerId":0},
			{"clientID":1,"firstName":"Ольга","lastName":"Безрукова","photoPath":"https://pp.userapi.com/c855520/v855520748/7fffb/b6Dn2JrI00I.jpg","trainerId":0}
		]
}*/
