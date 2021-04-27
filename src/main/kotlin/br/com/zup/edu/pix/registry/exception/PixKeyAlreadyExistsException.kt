package br.com.zup.edu.pix.registry.exception

import java.lang.RuntimeException

class PixKeyAlreadyExistsException(message: String = "Pix key already exists"): RuntimeException(message)