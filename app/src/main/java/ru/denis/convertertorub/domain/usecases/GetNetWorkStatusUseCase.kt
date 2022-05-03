package ru.denis.convertertorub.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.OutSharedUseCase
import ru.denis.convertertorub.util.NetWorkUtils
import javax.inject.Inject

class GetNetWorkStatusUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val netWorkUtils: NetWorkUtils
) : OutSharedUseCase<Boolean>(dispatcher) {
    override suspend fun execute() = netWorkUtils.hasNetworkConnection()
}