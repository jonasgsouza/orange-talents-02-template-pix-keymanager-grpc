package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcAccountType
import br.com.zup.edu.GrpcKeyType
import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.KeyManagerGrpcServiceGrpc
import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.repository.PixKeyRepository
import br.com.zup.integration.BCB
import br.com.zup.integration.ERPItau
import br.com.zup.integration.request.BankAccountRequest
import br.com.zup.integration.request.CreatePixKeyRequest
import br.com.zup.integration.request.OwnerRequest
import br.com.zup.integration.response.BankAccountQueryResponse
import br.com.zup.integration.response.HolderResponse
import br.com.zup.integration.response.InstitutionResponse
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest(transactional = false)
internal class RegisterPixKeyEndpointTest(
    val keyRepository: PixKeyRepository,
    val grpcClient: KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceBlockingStub
) {

    @Inject
    lateinit var bcb: BCB

    @Inject
    lateinit var erpItau: ERPItau

    companion object {
        val clientId = UUID.randomUUID()
    }

    @BeforeEach
    fun setUp() {
        keyRepository.deleteAll()
    }

    @Test
    fun `deveria cadastrar uma nova chave pix com sucesso`() {
        val bankAccountQueryResponse = BankAccountQueryResponse(
            bankAccountType = BankAccountQueryResponse.BankAccountType.CONTA_CORRENTE,
            agency = "0001",
            number = "00000000",
            institution = InstitutionResponse(
                name = "Itau",
                ispb = "00001"
            ),
            holder = HolderResponse(
                id = clientId,
                name = "Fulano",
                document = "12345678909"
            )
        )
        `when`(erpItau.findBankAccount(clientId.toString(), BankAccount.BankAccountType.CONTA_CORRENTE))
            .thenReturn(bankAccountQueryResponse)

        val crreatePixKeyRequest = CreatePixKeyRequest(
            keyType = CreatePixKeyRequest.PixKeyType.CPF,
            key = "12345678909",
            bankAccount = BankAccountRequest(
                participant = "00001",
                branch = "0001",
                accountNumber = "00000000",
                accountType = BankAccountRequest.BankAccountType.CACC
            ),
            owner = OwnerRequest(
                type = OwnerRequest.OwnerType.NATURAL_PERSON,
                name = "Fulano",
                taxIdNumber = "12345678909"
            )
        )
        `when`(bcb.createPixKey(crreatePixKeyRequest))
            .thenReturn(Any())

        val response = grpcClient.registerKey(
            GrpcRegisterPixKeyRequest.newBuilder()
                .setClientId(clientId.toString())
                .setKeyType(GrpcKeyType.CPF)
                .setKeyValue("12345678909")
                .setAccountType(GrpcAccountType.CONTA_CORRENTE)
                .build()
        )
        with(response) {
            assertNotNull(pixId)
            assertTrue(keyRepository.existsByUuid(UUID.fromString(pixId)))
        }
    }

    @MockBean(BCB::class)
    fun mockBcb(): BCB {
        return mock(BCB::class.java)
    }

    @MockBean(ERPItau::class)
    fun mockErpItau(): ERPItau {
        return mock(ERPItau::class.java)
    }

    @Factory
    class Client {

        @Singleton
        fun grpcClient(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceBlockingStub {
            return KeyManagerGrpcServiceGrpc.newBlockingStub(channel)
        }
    }
}