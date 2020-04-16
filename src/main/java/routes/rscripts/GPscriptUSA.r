library(GPfit)
dataUSA<-read.csv("rdata/estados_unidos.csv")

dataUSAMod<-dataUSA
usFirstDate<-as.Date("2020-01-21")
dataUSAMod$Casos<-NULL
dataUSAMod$Tipo_transmision<-as.numeric(dataUSAMod$Tipo_transmision)
dataUSAMod$Dias_ultimo_infectado<-NULL
dataUSAMod$Pico<-NULL
USAmodel<-GP_fit(dataUSAMod,dataUSA$Casos)
print(USAmodel)
predictUSA<-read.csv("rdata/predictUSA.csv")
prediccionUSA<-predict(USAmodel,predictUSA)
print(prediccionUSA)
