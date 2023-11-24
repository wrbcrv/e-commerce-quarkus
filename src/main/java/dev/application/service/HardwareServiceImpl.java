package dev.application.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import dev.application.dto.HardwareDTO;
import dev.application.dto.HardwareResponseDTO;
import dev.application.model.Categoria;
import dev.application.model.Hardware;
import dev.application.model.Status;
import dev.application.repository.FabricanteRepository;
import dev.application.repository.HardwareRepository;
import dev.application.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class HardwareServiceImpl implements HardwareService {

  @Inject
  HardwareRepository hardwareRepository;

  @Inject
  MarcaRepository marcaRepository;

  @Inject
  Validator validator;

  @Inject
  FabricanteRepository fabricanteRepository;

  @Override
  @Transactional
  public HardwareResponseDTO create(@Valid HardwareDTO hardwareDTO) throws ConstraintViolationException {
    validate(hardwareDTO);

    Hardware entity = new Hardware();

    entity.setMarca(marcaRepository.findById(hardwareDTO.idMarca()));
    entity.setModelo(hardwareDTO.modelo());
    entity.setFabricante(fabricanteRepository.findById(hardwareDTO.idFabricante()));
    entity.setLancamento(hardwareDTO.lancamento());
    entity.setNome(hardwareDTO.nome());
    entity.setPreco(hardwareDTO.preco());
    entity.setEstoque(hardwareDTO.estoque());
    entity.setCategoria(Categoria.valueOf(hardwareDTO.idCategoria()));
    entity.setStatus(Status.valueOf(hardwareDTO.idStatus()));

    hardwareRepository.persist(entity);

    return HardwareResponseDTO.valueOf(entity);
  }

  @Override
  @Transactional
  public HardwareResponseDTO update(Long id, HardwareDTO hardwareDTO) throws ConstraintViolationException {
    validate(hardwareDTO);

    Hardware entity = hardwareRepository.findById(id);

    entity.setMarca(marcaRepository.findById(hardwareDTO.idMarca()));
    entity.setModelo(hardwareDTO.modelo());
    entity.setFabricante(fabricanteRepository.findById(hardwareDTO.idFabricante()));
    entity.setLancamento(hardwareDTO.lancamento());
    entity.setNome(hardwareDTO.nome());
    entity.setPreco(hardwareDTO.preco());
    entity.setEstoque(hardwareDTO.estoque());
    entity.setCategoria(Categoria.valueOf(hardwareDTO.idCategoria()));
    entity.setStatus(Status.valueOf(hardwareDTO.idStatus()));

    return HardwareResponseDTO.valueOf(entity);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    hardwareRepository.deleteById(id);
  }

  @Override
  public List<HardwareResponseDTO> findAll(int page, int pageSize) {
    List<Hardware> list = hardwareRepository.findAll().page(page, pageSize).list();

    return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
  }

  @Override
  public HardwareResponseDTO findById(Long id) {
    Hardware hardware = hardwareRepository.findById(id);
    if (hardware == null)
      throw new NotFoundException("Hardware não encontrado.");

    return HardwareResponseDTO.valueOf(hardware);
  }

  @Override
  public List<HardwareResponseDTO> findByNome(String nome, int page, int pageSize) {
    List<Hardware> list = hardwareRepository.findByNome(nome).page(page, pageSize).list();

    return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
  }

  @Override
  public List<HardwareResponseDTO> findByModelo(String modelo, int page, int pageSize) {
    List<Hardware> list = hardwareRepository.findByModelo(modelo).page(page, pageSize).list();

    return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public HardwareResponseDTO saveImage(Long id, String imageName) {
    Hardware entity = hardwareRepository.findById(id);
    entity.setImageName(imageName);

    return HardwareResponseDTO.valueOf(entity);
  }

  @Override
  public long count() {
    return hardwareRepository.count();
  }

  @Override
  public long countByNome(String nome) {
    return hardwareRepository.findByNome(nome).count();
  }

  private void validate(HardwareDTO hardwareDTO) throws ConstraintViolationException {
    Set<ConstraintViolation<HardwareDTO>> violations = validator.validate(hardwareDTO);
    if (!violations.isEmpty())
      throw new ConstraintViolationException(violations);
  }

  @Override
  public byte[] createReportHardwares(String filter) {
    List<Hardware> lista = hardwareRepository.findByNome(filter).list();
    return gerarRelatorioPDF(lista);
  }

  public static byte[] gerarRelatorioPDF(List<Hardware> hardwareList) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try (PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos))) {
      Document document = new Document(pdfDocument);
      pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderFooterHandler());

      PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);

      float tamanhoFonte = 10f;

      document.add(new Paragraph("Relatório de Hardware")
          .setFontSize(16)
          .setBold()
          .setFont(font));

      document.add(new Paragraph("Detalhes dos Equipamentos")
          .setFontSize(14)
          .setItalic()
          .setFont(font));

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      String dataHora = sdf.format(new Date());
      document.add(new Paragraph("Data e Hora de Geração: " + dataHora).setFontSize(12).setFont(font));

      Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 2, 1 })).useAllAvailableWidth();
      table.addHeaderCell(new Cell().add(new Paragraph("ID")
          .setFontSize(tamanhoFonte)
          .setFont(font)));
      table.addHeaderCell(new Cell().add(new Paragraph("Nome")
          .setFontSize(tamanhoFonte)
          .setFont(font)));
      table.addHeaderCell(new Cell().add(new Paragraph("Preço")
          .setFontSize(tamanhoFonte)
          .setFont(font)));

      for (Hardware hardware : hardwareList) {
        table.addCell(new Cell().add(new Paragraph(String.valueOf(hardware.getId()))
            .setFontSize(tamanhoFonte)
            .setFont(font)));
        table.addCell(
            new Cell().add(new Paragraph(hardware.getNome())
                .setFontSize(tamanhoFonte)
                .setFont(font)));
        table.addCell(new Cell()
            .add(new Paragraph("R$" + hardware.getPreco())
                .setFontSize(tamanhoFonte)
                .setFont(font)));
      }

      document.add(table);

      document.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return baos.toByteArray();
  }
}

class HeaderFooterHandler implements IEventHandler {
  public void handleEvent(Event event) {
    PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
    PdfDocument pdf = docEvent.getDocument();
    PdfPage page = docEvent.getPage();
    int pageNum = pdf.getPageNumber(page);

    PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdf);
    canvas.beginText().setFontAndSize(pdf.getDefaultFont(), 12);

    canvas.moveText(34, 20).showText("Página " + pageNum);

    String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss"));
    canvas.moveText(500 - 80, 0).showText(dataHora);

    canvas.endText();
  }
}