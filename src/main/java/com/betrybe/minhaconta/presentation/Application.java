package com.betrybe.minhaconta.presentation;

import com.betrybe.minhaconta.business.EnergyAccount;
import com.betrybe.minhaconta.business.EnergyBill;
import com.ions.lightdealer.sdk.model.ElectronicDevice;
import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.service.LightDealerApi;

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
    // método inputClientCpf() da ConsoleUserInterface.
    String cpf = ui.inputClientCpf();
    Client cliente = api.findClient(cpf);

    if (cliente == null) {
      ui.showMessage("Pessoa cliente não encontrada!");
    } else {
      Address address = new Address();
      // cliente encontrad,um novo objeto do tipo Address,repassado ao método fillAddressData()
      // método fillAddressData pode ser encontrado na classe ConsoleUserInterface;
      ui.fillAddressData(address);
      api.addAddressToClient(address, cliente);
    }
  }

  /**
   * Req. 8 – Register address devices.
   */
  public void registerAddressDevices() {
    String register = ui.inputAddressRegistration();
    Address address = api.findAddress(register);
    if (address == null) {
      ui.showMessage("Endereço não encontrado!");
    } else {
      int numberDevices = ui.inputNumberOfDevices();

      for (int index = 0; index < numberDevices; index++) {
        ElectronicDevice device = new ElectronicDevice();
        ui.fillDeviceData(device);
        api.addDeviceToAddress(device, address);
      }
    }
  }

  /**
   * Req. 9 – Estimates the address energy bill.
   */
  public void estimateAddressBill() {
    String estimates = ui.inputAddressRegistration();
    Address address = api.findAddress(estimates);

    if (address == null) {
      ui.showMessage("Endereço não encontrado!");
    } else {
      EnergyBill energyBill = new EnergyBill(address, true);
      double estimateX = energyBill.estimate();
      ui.showMessage("Valor estimado para a conta: " + estimateX);
    }
  }

  /**
   * Req. 10 – Optimizes the energy bill.
   */
  public void optimizeEnergyBill() {
    String cpf = ui.inputClientCpf();
    Client cliente = api.findClient(cpf);

    if (cliente == null) {
      ui.showMessage("Pessoa cliente não encontrada!");
    } else { // deveremos instanciar um objeto da classe EnergyAccount
      // que está parcialmente implementada
      EnergyAccount energyAccount = new EnergyAccount(cliente);
      // método optimizeEnergyBill invocará o método suggestReducedUsage.
      suggestReducedUsage(energyAccount);
    }
  }

  /**
   * Req 10 - Aux. Method to display high consumptions devices.
   */
  public void suggestReducedUsage(EnergyAccount energyAccount) {
    ElectronicDevice[] devices = energyAccount.findHighConsumptionDevices();
    for (ElectronicDevice electronicDevice : devices) {
      ui.showMessage("Considere reduzir o uso dos seguintes dispositivos:" + electronicDevice.getName());
    }
  }
}