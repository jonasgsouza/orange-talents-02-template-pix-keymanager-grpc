package br.com.zup.edu.pix.registry


import br.com.zup.edu.AccountTypeGrpc
import br.com.zup.edu.KeyTypeGrpc
import br.com.zup.edu.RegisterPixKeyRequestGrpc
import br.com.zup.edu.RegisterPixKeyServiceGrpc
import br.com.zup.edu.integration.BCB
import br.com.zup.edu.integration.ERPItau
import br.com.zup.edu.integration.request.BankAccountRequest
import br.com.zup.edu.integration.request.CreatePixKeyRequest
import br.com.zup.edu.integration.request.OwnerRequest
import br.com.zup.edu.integration.response.BankAccountQueryResponse
import br.com.zup.edu.integration.response.HolderResponse
import br.com.zup.edu.integration.response.InstitutionResponse
import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.repository.PixKeyRepository
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest(transactional = false)
internal class RegisterPixKeyEndpointTest(
    val keyRepository: PixKeyRepository,
    val grpcClient: RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub
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
            RegisterPixKeyRequestGrpc.newBuilder()
                .setClientId(clientId.toString())
                .setKeyType(KeyTypeGrpc.CPF)
                .setKeyValue("12345678909")
                .setAccountType(AccountTypeGrpc.CONTA_CORRENTE)
                .build()
        )
        with(response) {
            assertNotNull(pixId)
            assertTrue(keyRepository.findByUuid(UUID.fromString(pixId)).isPresent)
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
        fun grpcClient(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub {
            return RegisterPixKeyServiceGrpc.newBlockingStub(channel)
        }
    }
}