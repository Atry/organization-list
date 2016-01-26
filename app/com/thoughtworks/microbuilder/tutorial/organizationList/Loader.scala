package com.thoughtworks.microbuilder.tutorial.organizationList

import com.thoughtworks.microbuilder.play.PlayOutgoingJsonService
import com.thoughtworks.microbuilder.tutorial.githubSdk.proxy.MicrobuilderOutgoingProxyFactory._
import com.thoughtworks.microbuilder.tutorial.githubSdk.proxy.MicrobuilderRouteConfigurationFactory._
import com.thoughtworks.microbuilder.tutorial.githubSdk.rpc.IOrganizationService
import com.thoughtworks.microbuilder.tutorial.organizationList.controllers.OrganizationListController
import play.api.libs.ws.ning.NingWSComponents
import play.api.{BuiltInComponentsFromContext, Application, ApplicationLoader}
import play.api.ApplicationLoader.Context
import router.Routes

class Loader extends ApplicationLoader {
  override def load(context: Context): Application = {

    val components = new BuiltInComponentsFromContext(context) with NingWSComponents {
      implicit def executionContext = actorSystem.dispatcher

      lazy val organizationService = PlayOutgoingJsonService.newProxy[IOrganizationService]("https://api.github.com/", wsApi)

      override lazy val router = new Routes(httpErrorHandler, new OrganizationListController(organizationService)(actorSystem.dispatcher))
    }

    components.application
  }
}