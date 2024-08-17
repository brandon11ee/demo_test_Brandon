import string
import random


def generador_contrasena(longitud):
    caracteres = "abcdrhijklmnopqrstxwvyz,.<>/'[]=-+_?"
    contrasena = ""

    for i in range(longitud):
        contrasena += random.choice(caracteres)

    return contrasena


longitud = int(input("cual es la longitud de la contrasena deseada: "))

nueva_contrasena = generador_contrasena(longitud)

print("nueva contyrasena es:", nueva_contrasena)