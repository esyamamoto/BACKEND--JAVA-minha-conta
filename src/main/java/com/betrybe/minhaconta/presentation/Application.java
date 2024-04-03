package com.betrybe.minhaconta.presentation;

import com.betrybe.minhaconta.business.EnergyAccount;
import com.betrybe.minhaconta.business.EnergyBill;
import com.ions.lightdealer.sdk.model.ElectronicDevice;
import com.ions.lightdealer.sdk.service.LightDealerApi;
import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;

/**
 * The type Application.
 */
public class Application {

  ConsoleUserInterface ui;
  LightDealerApi api;

  /**
   * Constructor that instantiates a new Application.
   */
  public Application(ConsoleUserInterface ui) {
    this.ui = ui;
    this.api = new LightDealerApi();
  }

  /**
   * Req. 4 – Creates CLI menu.
   */
  public void run() {
    char menuOption;
    String[] options = {
        "1 - Cadastrar cliente",
        "2 - Cadastrar imóvel de cliente",
        "3 - Cadastrar dispositivos em imóvel",
        "4 - Estimar conta de imóvel",
        "5 - Otimizar uso de energia",
        "6 - Sair"
    };
    do {
      menuOption = ui.inputMenuOption(options);
      this.runOptionAction(menuOption);
    } while (menuOption != '6');
  }

  /**
   * Req. 5 – Run menu options.
   */
  public void runOptionAction(char option) { // https://www.w3schools.com/java/java_switch.asp
    switch (option) {
      case '1':
        registerClient();
        break;
      case '2':
        registerClientAddress();
        break;
      case '3':
        registerAddressDevices();
        break;
      case '4':
        estimateAddressBill();
        break;
      case '5':
        optimizeEnergyBill();
        break;
      case '6':
        ui.showMessage("Volte sempre!");
        break;
      default:
        ui.showMessage("Opção inválida!"); // método showMessage() da classe ConsoleUserInterface
    }
  }

  /**
   * Req. 6 – Register client.
   */
  public void registerClient() {
    Client newClient = new Client(); // importaaaaaaaaaaaaa
    this.ui.fillClientData(newClient); // método fillClientData() da classe ConsoleUserInterface
    this.api.addClient(newClient); // método addClient() da classe LightDealerApi
  }

  /**
   * Req. 7 – Register client address.
   */
  public void registerClientAddress() {
  }

  /**
   * Req. 8 – Register address devices.
   */
  public void registerAddressDevices() {
  }

  /**
   * Req. 9 – Estimates the address energy bill.
   */
  public void estimateAddressBill() {
  }

  /**
   * Req. 10 – Optimizes the energy bill.
   */
  public void optimizeEnergyBill() {
  }

  /**
   * Req 10 - Aux. Method to display high consumptions devices.
   */
  public void suggestReducedUsage(EnergyAccount energyAccount) {
  }
}
