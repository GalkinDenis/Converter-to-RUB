package ru.denis.convertertorub.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.usecases.baseusecases.OutSharedUseCase
import ru.denis.convertertorub.util.NetWorkUtils
import javax.inject.Inject

@ViewModelScoped
class GetNetWorkStatusUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val netWorkUtils: NetWorkUtils
) : OutSharedUseCase<Boolean>(dispatcher) {

    override suspend fun execute(): Boolean = netWorkUtils.hasNetworkConnection()
}