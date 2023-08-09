package enumeratum

import enumeratum.CaseMappers.EnumeratumMapper
import enumeratum._
import magnolify.shared.{CaseMapper, EnumType}
// Need to add dependencies to be able to import this
//import com.google.common.base.CaseFormat

sealed trait UbiquityIntegrationId extends EnumEntry with EnumEntry.Snakecase

object UbiquityIntegrationId extends Enum[UbiquityIntegrationId] {
  val values = findValues

  implicit val enumTypeUbiquityIntegrationId =
    EnumType[UbiquityIntegrationId](EnumeratumMapper(UbiquityIntegrationId))

  case object Alexa extends UbiquityIntegrationId
}

object CaseMappers {
//  val SnakeCaseMapper = CaseMapper(
//    CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert
//  )

  def EnumeratumMapper[S <: EnumEntry](enum: Enum[S]): CaseMapper = {
    val enumEntries = enum.values.map(x => (x.toString, x.entryName)).toMap
    CaseMapper(className => enumEntries.getOrElse(className, className))
  }
}
